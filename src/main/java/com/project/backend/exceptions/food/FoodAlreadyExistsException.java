package com.project.backend.exceptions.food;

public class FoodAlreadyExistsException extends RuntimeException {
    public FoodAlreadyExistsException() {
        super("Food already exists in database!");
    }
}
