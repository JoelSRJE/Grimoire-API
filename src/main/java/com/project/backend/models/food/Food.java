package com.project.backend.models.food;

import com.project.backend.models.ingredients.Ingredient;
import com.project.backend.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private String foodName;

    @Column()
    private FoodType foodType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredientList = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private UUID owner;

    public Food() {}

    public Food(String foodName, FoodType foodType ,List<Ingredient> ingredientList, UUID owner) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.ingredientList = ingredientList;
        this.createdAt = LocalDateTime.now();
        this.owner = owner;
    }
}
