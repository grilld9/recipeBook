package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ru.nsu.fit.repiceBook.model.enums.RecipeStatus;

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
    @Column(length = 10000)
    private String description;

    @OneToMany(orphanRemoval = true, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Step> steps;

    @Column(name="image_id")
    private Long imageId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RecipeStatus status = RecipeStatus.IN_CREATING_PROCESS;
}
