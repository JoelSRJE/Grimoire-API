package com.project.backend.exceptions.food;

public class FoodTypeIsNullException extends RuntimeException {
    public FoodTypeIsNullException() {
        super("FoodType cannot be null!");
    }
}
