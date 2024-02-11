package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Ingredient> ingredients;
}
