package ru.nsu.fit.repiceBook.repositories.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select r from Recipe r join r.tags t where t.id in :tagIds and lower(r.name) like lower(concat('%', :name, '%'))")
    Page<Recipe> searchRecipes(@Param("name") String name, @Param("tagIds") List<Long> tagIds, Pageable pageable);

    Optional<Page<Recipe>> findByTagsInAndNameContainingIgnoreCase(List<Tag> tag, String name, Pageable pageable);
}
