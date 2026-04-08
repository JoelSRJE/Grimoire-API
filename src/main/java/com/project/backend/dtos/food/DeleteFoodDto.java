package com.project.backend.dtos.food;

public record DeleteFoodDto(
        String message
) {
    public static DeleteFoodDto from(String message) {
        return new DeleteFoodDto(message);
    }
}
