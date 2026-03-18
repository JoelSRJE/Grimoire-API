package com.project.backend.repositories;

import com.project.backend.models.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    Optional<Food> findByFoodName(String foodName);
    boolean existsByFoodName(String foodName);
}
