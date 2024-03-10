package ru.nsu.fit.repiceBook.services.repositories.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.repiceBook.model.SourceIngredient;

@Repository
public interface SourceIngredientRepository extends JpaRepository<SourceIngredient, Long> {

    boolean existsByName(String name);
}
