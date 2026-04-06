package com.project.backend.exceptions.ingredient;

public class DidntFindAnIngredientException extends RuntimeException {
    public DidntFindAnIngredientException() {
        super("No such ingredient in database!");
    }
}
