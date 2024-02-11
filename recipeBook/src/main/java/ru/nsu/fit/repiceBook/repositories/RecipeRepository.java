package ru.nsu.fit.repiceBook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.repiceBook.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
