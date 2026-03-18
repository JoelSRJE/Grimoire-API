package com.project.backend.requests.food;

import com.project.backend.models.food.FoodType;
import com.project.backend.requests.ingredient.IngredientRequest;

import java.util.List;

public record RegisterFoodRequest(
        String foodName,
        List<IngredientRequest> ingredients,
        FoodType foodType
) {
}
