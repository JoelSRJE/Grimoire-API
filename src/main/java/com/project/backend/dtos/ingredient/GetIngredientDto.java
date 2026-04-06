package com.project.backend.dtos.ingredient;

import com.project.backend.dtos.food.GetFoodDto;
import com.project.backend.models.ingredients.Ingredient;

import java.util.UUID;

public record GetIngredientDto(
        UUID ingredientId,
        String ingredientName,
        GetFoodDto food
) {
    public static GetIngredientDto from(Ingredient ingredient) {
        return new GetIngredientDto(
                ingredient.getIngredientId(),
                ingredient.getIngredientName(),
                GetFoodDto.from(ingredient.getFood())
        );
    }
}
