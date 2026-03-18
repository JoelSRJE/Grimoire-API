package com.project.backend.exceptions.food;

public class FoodListIsEmptyException extends RuntimeException {
    public FoodListIsEmptyException() {
        super("The list of foods is empty!");
    }
}
