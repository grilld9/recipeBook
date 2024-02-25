package ru.nsu.fit.repiceBook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.repiceBook.model.SourceIngredient;

public interface SourceIngredientRepository extends JpaRepository<SourceIngredient, Long> {

    boolean existsByName(String name);
}
