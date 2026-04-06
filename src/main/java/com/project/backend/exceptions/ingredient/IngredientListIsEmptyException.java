package com.project.backend.exceptions.ingredient;

public class IngredientListIsEmptyException extends RuntimeException {
    public IngredientListIsEmptyException() {
        super("The ingredient list is empty or null!");
    }
}
