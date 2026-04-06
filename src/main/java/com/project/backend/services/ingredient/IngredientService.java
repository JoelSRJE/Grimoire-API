package com.project.backend.services.ingredient;

import com.project.backend.dtos.ingredient.GetIngredientDto;
import com.project.backend.dtos.ingredient.IngredientDto;
import com.project.backend.exceptions.ingredient.*;
import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.repositories.IngredientRepository;
import com.project.backend.requests.ingredient.UpdateIngredientRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientService implements IIngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<GetIngredientDto> getSpecificIngredients(String ingredientName) {

        if (ingredientName == null || ingredientName.isBlank()) {
            throw new IngredientNameIsEmptyException();
        }

        List<Ingredient> ingredients = ingredientRepository.findAllByIngredientName(ingredientName);

        if (ingredients == null || ingredients.isEmpty()) {
            throw new IngredientListIsEmptyException();
        }

        List<GetIngredientDto> ingredientDtoList = ingredients.stream()
                .map(GetIngredientDto::from)
                .toList();

        return ingredientDtoList;
    }

    @Override
    public Ingredient getSpecificIngredient(UUID ingredientId) {
        if (ingredientId == null) {
            throw new IngredientIdIsNullException();
        }

        Ingredient foundIngredient = ingredientRepository.findByIngredientId(ingredientId);

        if (foundIngredient.getIngredientName().isBlank()) {
            throw new DidntFindAnIngredientException();
        }

        return foundIngredient;
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        List<Ingredient> ingredientList = ingredientRepository.findAll();

        if (ingredientList.isEmpty()) {
            throw new IngredientListIsEmptyException();
        }

        List<IngredientDto> ingredientDtoList = ingredientList.stream()
                .map(IngredientDto::from)
                .toList();

        if (ingredientList.isEmpty()) {
            throw new IngredientDtoListIsEmptyException();
        }

        return ingredientDtoList;
    }

    @Override
    public GetIngredientDto updateSpecificIngredient(UUID ingredientId, UpdateIngredientRequest request) {

        if (request.ingredientName() == null || request.ingredientName().isBlank()) {
            throw new IngredientNameIsEmptyException();
        }

        Ingredient updatedIngredient = getSpecificIngredient(ingredientId);
        updatedIngredient.setIngredientName(request.ingredientName());

        ingredientRepository.save(updatedIngredient);

        GetIngredientDto ingredientDto = GetIngredientDto.from(updatedIngredient);

        return ingredientDto;
    }

    @Override
    @Transactional
    public String deleteIngredient(UUID ingredientId) {
        if (ingredientId == null) {
            throw new IngredientIdIsNullException();
        }

        int deleted = ingredientRepository.deleteByIngredientId(ingredientId);

        if (deleted == 0) {
            throw new DidntFindAnIngredientException();
        }

        return "Successfully deleted: " + ingredientId + "!";
    }
}
