package com.project.backend.models.ingredients;

import com.project.backend.models.food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ingredientId;

    @Column(nullable = false, unique = true)
    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public Ingredient() {}

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
