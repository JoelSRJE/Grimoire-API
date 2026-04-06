package com.project.backend.dtos.ingredient;

import com.project.backend.models.ingredients.Ingredient;

import java.util.UUID;

public record IngredientDto(
        UUID ingredientId,
        String ingredientName
) {
    public static IngredientDto from(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getIngredientId(),
                ingredient.getIngredientName()
        );
    }
}
