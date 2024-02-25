package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.nio.file.Path;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Path imagePath;

    @OneToMany
    private List<Ingredient> ingredients;

}
