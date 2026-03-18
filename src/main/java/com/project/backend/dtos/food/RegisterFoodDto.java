package com.project.backend.dtos.food;

import com.project.backend.dtos.ingredient.IngredientDto;
import com.project.backend.models.food.Food;
import com.project.backend.models.food.FoodType;
import com.project.backend.models.ingredients.Ingredient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RegisterFoodDto(
        UUID foodId,
        String foodName,
        FoodType foodType,
        List<IngredientDto> ingredientList,
        LocalDateTime createdAt
) {
    public static RegisterFoodDto from(Food food) {
        return new RegisterFoodDto(
                food.getFoodId(),
                food.getFoodName(),
                food.getFoodType(),
                food.getIngredientList().stream()
                                .map(ing -> new IngredientDto(ing.getIngredientName()))
                                        .toList(),
                food.getCreatedAt()
        );
    }
}
