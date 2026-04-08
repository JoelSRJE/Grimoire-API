package com.project.backend.services.food;

import com.project.backend.exceptions.food.*;
import com.project.backend.models.food.Food;
import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.models.user.User;
import com.project.backend.repositories.FoodRepository;
import com.project.backend.requests.food.DeleteFoodsRequest;
import com.project.backend.requests.food.RegisterFoodRequest;
import com.project.backend.requests.food.UpdateFoodRequest;
import com.project.backend.services.ingredient.IngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService implements IFoodService {

    private final FoodRepository foodRepository;
    private final IngredientService ingredientService;

    @Override
    @Transactional
    public Food registerFood(RegisterFoodRequest request, User authenticatedUser) {

        if (request.foodName() == null || request.foodName().isBlank()) {
            throw new FoodNameIsNullException();
        }

        if (request.foodType() == null) {
            throw new FoodTypeIsNullException();
        }

        if (request.ingredients() == null || request.ingredients().isEmpty()) {
            throw new FoodIngredientListIsEmptyException();
        }

        if (foodRepository.existsByFoodName(request.foodName())) {
            throw new FoodAlreadyExistsException();
        }

        Food food = new Food();
        food.setFoodName(request.foodName());
        food.setFoodType(request.foodType());
        food.setCreatedAt(LocalDateTime.now());
        food.setOwner(authenticatedUser.getUserId());

        List<Ingredient> ingredients = request.ingredients().stream()
                .filter(Objects::nonNull)
                .map(ing -> {
                    if (ing.ingredientName() == null || ing.ingredientName().isBlank()) {
                        throw new RuntimeException("Ingredient name is invalid");
                    }

                    Ingredient ingredient = new Ingredient();
                    ingredient.setIngredientName(ing.ingredientName());
                    ingredient.setFood(food);
                    return ingredient;
                })
                .collect(Collectors.toList());

        food.setIngredientList(ingredients);

        return foodRepository.save(food);
    }

    @Override
    public Food getSpecificFood(String foodName) {

        if (foodName == null || foodName.isBlank()) {
            throw new FoodNameIsNullException();
        }

        Optional<Food> food = foodRepository.findByFoodName(foodName);

        if (food == null ||food.isEmpty()) {
            throw new FoodDoesntExistInDatabaseException();
        }

        Food foundFood = food.get();

        return foundFood;
    }

    @Override
    public List<Food> getAllFoods(User authenticatedUser) {
        List<Food> foodList = foodRepository.findAllByOwner(authenticatedUser.getUserId());

        return foodList != null ? foodList : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Food> updateFood(UpdateFoodRequest request) {

        if (request.foods() == null || request.foods().isEmpty()) {
            throw new RuntimeException("Ingen mat att uppdatera");
        }

        List<Food> updatedFoods = request.foods().stream()
                .map(item -> {
                    Food food = foodRepository.findById(item.foodId())
                            .orElseThrow(() -> new FoodDoesntExistInDatabaseException());

                    if (item.foodName() != null && !item.foodName().isBlank()) {
                        food.setFoodName(item.foodName());
                    }

                    if (item.ingredients() != null) {
                        food.getIngredientList().clear();

                        item.ingredients().stream()
                                .filter(ingReq -> ingReq.ingredientName() != null && !ingReq.ingredientName().isBlank())
                                .forEach(ingReq -> {
                                    Ingredient ingredient = new Ingredient();
                                    ingredient.setIngredientName(ingReq.ingredientName());
                                    ingredient.setFood(food);
                                    food.getIngredientList().add(ingredient);
                                });
                    }

                    return foodRepository.save(food);
                })
                .toList();

        return updatedFoods;
    }

    @Override
    public String deleteFood(UUID foodId) {
        Optional<Food> foundFood = foodRepository.findById(foodId);

        if (foundFood.isEmpty()) {
            throw new FoodDoesntExistInDatabaseException();
        }

        foodRepository.deleteById(foodId);

        return "Successfully deleted object: " + foundFood.get().getFoodName() + "!";
    }

    @Override
    @Transactional
    public String deleteFoods(DeleteFoodsRequest request) {

        if (request.foodIds() == null || request.foodIds().isEmpty()) {
            throw new FoodListIsEmptyException();
        }

        List<UUID> foodUUIDs = request.foodIds().stream()
                .map(UUID::fromString)
                .toList();

        List<Food> foodsToDelete = foodRepository.findAllById(foodUUIDs);

        if (foodsToDelete.isEmpty()) {
            throw new FoodListIsEmptyException();
        }

        foodRepository.deleteAll(foodsToDelete);

        return "Successfully deleted: " + foodsToDelete.stream().count();
    }

}
