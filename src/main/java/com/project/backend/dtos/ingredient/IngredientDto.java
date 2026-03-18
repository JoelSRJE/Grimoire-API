package com.project.backend.dtos.ingredient;

import com.project.backend.models.ingredients.Ingredient;

public record IngredientDto(
        String ingredientName
) {
    public static IngredientDto from(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getIngredientName()
        );
    }
}
