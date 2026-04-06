package com.project.backend.services.ingredient;

import com.project.backend.dtos.ingredient.GetIngredientDto;
import com.project.backend.dtos.ingredient.IngredientDto;
import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.requests.ingredient.UpdateIngredientRequest;

import java.util.List;
import java.util.UUID;

public interface IIngredientService {
    List<GetIngredientDto> getSpecificIngredients(String ingredientName);
    Ingredient getSpecificIngredient(UUID ingredientId);
    List<IngredientDto> getAllIngredients();
    GetIngredientDto updateSpecificIngredient(UUID ingredientId, UpdateIngredientRequest request);
    String deleteIngredient(UUID ingredientId);

    /*
    * Lista för att hämta alla per maträtt?
    *
    *  */
}
