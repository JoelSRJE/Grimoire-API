package com.project.backend.services.food;

import com.project.backend.dtos.food.GetFoodDto;
import com.project.backend.exceptions.food.*;
import com.project.backend.models.food.Food;
import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.repositories.FoodRepository;
import com.project.backend.requests.food.RegisterFoodRequest;
import com.project.backend.requests.food.UpdateFoodRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService implements IFoodService {

    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public Food registerFood(RegisterFoodRequest request) {

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
    public List<Food> getAllFoods() {
        List<Food> foodList = foodRepository.findAll();

        if (foodList == null || foodList.isEmpty()) {
            throw new FoodListIsEmptyException();
        }

        return foodList;
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

                    if (item.ingredients() != null && !item.ingredients().isEmpty()) {
                        food.getIngredientList().clear();
                        List<Ingredient> updatedIngredients = item.ingredients().stream()
                                .map(ingReq -> {
                                    Ingredient ingredient = new Ingredient();
                                    ingredient.setIngredientName(ingReq.ingredientName());
                                    ingredient.setFood(food);
                                    return ingredient;
                                }).toList();
                        food.setIngredientList(updatedIngredients);
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
}
