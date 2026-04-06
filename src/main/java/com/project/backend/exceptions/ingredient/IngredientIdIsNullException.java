package com.project.backend.exceptions.ingredient;

public class IngredientIdIsNullException extends RuntimeException {
    public IngredientIdIsNullException() {
        super("Ingredient Id is null or empty!");
    }
}
