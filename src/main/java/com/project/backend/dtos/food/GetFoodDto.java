package com.project.backend.dtos.food;

import com.project.backend.dtos.ingredient.IngredientDto;
import com.project.backend.models.food.Food;
import com.project.backend.models.food.FoodType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetFoodDto(
        UUID foodId,
        String foodName,
        FoodType foodType,
        List<IngredientDto> ingredients,
        LocalDateTime createdAt
) {
    public static GetFoodDto from(Food food) {
        return new GetFoodDto(
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
