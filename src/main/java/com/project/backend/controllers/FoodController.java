package com.project.backend.controllers;

import com.project.backend.dtos.food.GetFoodDto;
import com.project.backend.dtos.food.RegisterFoodDto;
import com.project.backend.models.food.Food;
import com.project.backend.requests.food.RegisterFoodRequest;
import com.project.backend.requests.food.UpdateFoodRequest;
import com.project.backend.services.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @PostMapping
    public ResponseEntity<RegisterFoodDto> registerFood(
            @RequestBody RegisterFoodRequest request
            ) {
        Food food = foodService.registerFood(request);

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
    public ResponseEntity<List<GetFoodDto>> getAllFoods() {
        List<Food> foodList = foodService.getAllFoods();

        List<GetFoodDto> foodDtoList = foodList.stream()
                .map(GetFoodDto::from)
                .toList();

        return ResponseEntity.ok(foodDtoList);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<List<GetFoodDto>> updateFood(
            @RequestBody UpdateFoodRequest request
            ) {
        List<Food> updatedFoodList = foodService.updateFood(request);

        List<GetFoodDto> updatedFoodDtos = updatedFoodList.stream()
                .map(GetFoodDto::from)
                .toList();

        return ResponseEntity.ok(updatedFoodDtos);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable UUID foodId) {
        String response = foodService.deleteFood(foodId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
