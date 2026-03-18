package com.project.backend.requests.food;

import com.project.backend.requests.ingredient.IngredientRequest;

import java.util.List;
import java.util.UUID;

public record UpdateFoodRequestObject(
        UUID foodId,
        String foodName,
        List<IngredientRequest> ingredients
) {
}
