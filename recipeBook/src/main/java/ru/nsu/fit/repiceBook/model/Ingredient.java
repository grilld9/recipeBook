package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@Builder
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Float weight;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
