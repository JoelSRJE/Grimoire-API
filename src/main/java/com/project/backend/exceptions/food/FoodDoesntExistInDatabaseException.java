package com.project.backend.exceptions.food;

public class FoodDoesntExistInDatabaseException extends RuntimeException {
    public FoodDoesntExistInDatabaseException() {
        super("The food doesn't exist in database!");
    }
}
