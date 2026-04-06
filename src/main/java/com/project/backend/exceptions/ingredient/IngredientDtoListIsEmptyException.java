package com.project.backend.exceptions.ingredient;

public class IngredientDtoListIsEmptyException extends RuntimeException {
    public IngredientDtoListIsEmptyException() {
        super("The ingredient dto list is empty or null!");
    }
}
