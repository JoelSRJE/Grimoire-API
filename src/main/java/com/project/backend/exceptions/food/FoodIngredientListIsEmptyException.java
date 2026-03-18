package com.project.backend.exceptions.food;

public class FoodIngredientListIsEmptyException extends RuntimeException {
    public FoodIngredientListIsEmptyException() {
        super("Ingredients in foods cannot be empty or null!");
    }
}
