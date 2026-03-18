package com.project.backend.exceptions.food;

public class FoodNameIsNullException extends RuntimeException {
    public FoodNameIsNullException() {
        super("Food name cannot be null or empty!");
    }
}
