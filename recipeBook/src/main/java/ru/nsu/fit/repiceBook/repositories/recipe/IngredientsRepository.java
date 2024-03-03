package ru.nsu.fit.repiceBook.repositories.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.repiceBook.model.Ingredient;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
}
