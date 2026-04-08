package com.project.backend.controllers;

import com.project.backend.dtos.food.DeleteFoodDto;
import com.project.backend.dtos.food.GetFoodDto;
import com.project.backend.dtos.food.RegisterFoodDto;
import com.project.backend.models.food.Food;
import com.project.backend.models.user.User;
import com.project.backend.requests.food.DeleteFoodsRequest;
import com.project.backend.requests.food.RegisterFoodRequest;
import com.project.backend.requests.food.UpdateFoodRequest;
import com.project.backend.services.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<RegisterFoodDto> registerFood(
            @RequestBody RegisterFoodRequest request,
            @AuthenticationPrincipal User authenticatedUser
            ) {
        Food food = foodService.registerFood(request, authenticatedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterFoodDto.from(food));
    }

    @GetMapping("/{foodName}")
    public ResponseEntity<GetFoodDto> getSpecificFood(
            @PathVariable String foodName
    ) {
        Food food = foodService.getSpecificFood(foodName);

        return ResponseEntity.ok(GetFoodDto.from(food));
    }

    @GetMapping
    public ResponseEntity<List<GetFoodDto>> getAllFoods(
            @AuthenticationPrincipal User authenticatedUser
    ) {
        List<Food> foodList = foodService.getAllFoods(authenticatedUser);

        List<GetFoodDto> foodDtoList = foodList.stream()
                .map(GetFoodDto::from)
                .toList();

        return ResponseEntity.ok(foodDtoList);
    }

    @PutMapping("/update-several")
    public ResponseEntity<List<GetFoodDto>> updateMultipleFoods(
            @RequestBody UpdateFoodRequest request,
            @AuthenticationPrincipal User authenticatedUser
            ) {
        List<Food> updatedFoodList = foodService.updateFood(request);

        List<GetFoodDto> updatedFoodDtos = updatedFoodList.stream()
                .map(GetFoodDto::from)
                .toList();

        return ResponseEntity.ok(updatedFoodDtos);
    }

    @DeleteMapping("delete-several")
    public ResponseEntity<DeleteFoodDto> deleteSeveralDifferentFoods(
            @RequestBody DeleteFoodsRequest request,
            @AuthenticationPrincipal User authenticatedUser
            ) {

        String message = foodService.deleteFoods(request);

        return ResponseEntity.ok(DeleteFoodDto.from(message));
    }
}
