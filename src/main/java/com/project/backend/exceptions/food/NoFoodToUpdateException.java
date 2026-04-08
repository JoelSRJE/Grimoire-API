package com.project.backend.exceptions.food;

public class NoFoodToUpdateException extends RuntimeException {
    public NoFoodToUpdateException() {
        super("No such food to update!");
    }
}
