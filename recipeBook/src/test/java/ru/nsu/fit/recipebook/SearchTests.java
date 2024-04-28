package ru.nsu.fit.recipebook;

import io.jsonwebtoken.lang.Assert;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.nsu.fit.repiceBook.RecipeBookApplication;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.Tag;
import ru.nsu.fit.repiceBook.model.enums.RecipeStatus;
import ru.nsu.fit.repiceBook.repositories.recipe.RecipeRepository;
import ru.nsu.fit.repiceBook.repositories.recipe.TagRepository;
import ru.nsu.fit.repiceBook.services.recipe.RecipeService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = RecipeBookApplication.class)
public class SearchTests {

    @Autowired
    public TagRepository tagRepository;

    @Autowired
    public RecipeService recipeService;

    @Autowired
    public RecipeRepository recipeRepository;


    @BeforeEach
    public void initRecipes() {
        recipeRepository.save(new Recipe(null, "Курица в сливках",
                "Курица в сливках", null, null,
                null, null, RecipeStatus.IN_CREATING_PROCESS,
                Set.of(tagRepository.findByName("обед").get(), tagRepository.findByName("ужин").get())));
        recipeRepository.save(new Recipe(null, "Мясо по-французски",
                null, null, null,
                null, null, RecipeStatus.IN_CREATING_PROCESS,
                Set.of(tagRepository.findByName("обед").get(), tagRepository.findByName("ужин").get())));
        recipeRepository.save(new Recipe(null, "Яичница",
                null, null, null,
                null, null, RecipeStatus.IN_CREATING_PROCESS,
                Set.of(tagRepository.findByName("завтрак").get(), tagRepository.findByName("быстро").get(),
                tagRepository.findByName("просто").get())));
    }

    @Test
    @Transactional
    public void searchByTags() {
        Tag lunch = tagRepository.findByName("обед").get();
        Tag simple = tagRepository.findByName("просто").get();
        Tag fast = tagRepository.findByName("быстро").get();
        Optional<Page<Recipe>> recipe = recipeRepository.findByTagsInAndNameContainingIgnoreCase(List.of(lunch), "", PageRequest.of(0, 2));
        Assertions.assertTrue(recipe.isPresent());
        Assertions.assertTrue(recipe.get().get()
                .map(Recipe::getName)
                .collect(Collectors.toSet())
                .containsAll(List.of("Курица в сливках", "Мясо по-французски")));
        recipe = recipeRepository.findByTagsInAndNameContainingIgnoreCase(List.of(simple, fast), "", PageRequest.of(0, 1));
        Assertions.assertTrue(recipe.isPresent());
        Assertions.assertTrue(recipe.get().get()
                .map(Recipe::getName)
                .collect(Collectors.toSet())
                .contains("Яичница"));
    }

}
