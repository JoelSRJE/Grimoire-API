package com.project.backend.exceptions.ingredient;

public class IngredientNameIsEmptyException extends RuntimeException {
    public IngredientNameIsEmptyException() {
        super("Ingredient name cannot be empty or null!");
    }
}
