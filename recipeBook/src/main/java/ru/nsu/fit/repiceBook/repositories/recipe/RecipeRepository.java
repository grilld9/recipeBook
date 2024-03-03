package ru.nsu.fit.repiceBook.repositories.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.repiceBook.model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<List<Recipe>> findByUserId(Long userId);
}
