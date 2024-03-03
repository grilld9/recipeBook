package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String imagePath;

    @OneToMany(orphanRemoval = true, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Step> steps;
}
