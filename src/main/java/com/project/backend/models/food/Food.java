package com.project.backend.models.food;

import com.project.backend.models.ingredients.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "foods")
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID foodId;

    @Column(nullable = false, unique = true)
    private String foodName;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredientList;

    public Food() {}

    public Food(String foodName, List<Ingredient> ingredientList) {
        this.foodName = foodName;
        this.ingredientList = ingredientList;
    }
}
