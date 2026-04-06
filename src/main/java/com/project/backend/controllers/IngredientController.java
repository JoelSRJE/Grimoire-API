package com.project.backend.controllers;

import com.project.backend.dtos.ingredient.GetIngredientDto;
import com.project.backend.dtos.ingredient.IngredientDto;
import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.models.user.User;
import com.project.backend.requests.ingredient.IngredientRequest;
import com.project.backend.requests.ingredient.UpdateIngredientRequest;
import com.project.backend.services.ingredient.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/{ingredientName}")
    public ResponseEntity<List<GetIngredientDto>> getSpecificIngredient(
            @PathVariable String ingredientName
    ) {
        List<GetIngredientDto> ingredientDtoList = ingredientService.getSpecificIngredients(ingredientName);

        return ResponseEntity.ok(ingredientDtoList);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients(
            @AuthenticationPrincipal User authenticatedUser
            ) {
        List<IngredientDto> ingredientDtoList = ingredientService.getAllIngredients();

        return ResponseEntity.ok(ingredientDtoList);
    }

    @PutMapping("/{ingredientId}")
    public ResponseEntity<GetIngredientDto> updateIngredient(
            @PathVariable UUID ingredientId,
            @RequestBody UpdateIngredientRequest request
            ) {
        GetIngredientDto ingredientDto = ingredientService.updateSpecificIngredient(ingredientId, request);

        return ResponseEntity.ok(ingredientDto);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(
            @PathVariable UUID ingredientId
    ) {
        String response = ingredientService.deleteIngredient(ingredientId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
