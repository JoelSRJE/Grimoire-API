package com.project.backend.services.food;

import com.project.backend.models.food.Food;
import com.project.backend.models.user.User;
import com.project.backend.requests.food.DeleteFoodsRequest;
import com.project.backend.requests.food.RegisterFoodRequest;
import com.project.backend.requests.food.UpdateFoodRequest;

import java.util.List;
import java.util.UUID;

public interface IFoodService {
    Food registerFood(RegisterFoodRequest request, User autheticatedUser);
    Food getSpecificFood(String foodName);
    List<Food> getAllFoods(User authenticatedUser);
    List<Food> updateFood(UpdateFoodRequest request);
    String deleteFood(UUID foodId);
    String deleteFoods(DeleteFoodsRequest request);
}
