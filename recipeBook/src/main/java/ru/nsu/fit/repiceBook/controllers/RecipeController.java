package ru.nsu.fit.repiceBook.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.repiceBook.dto.AddRecipeRequest;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @PostMapping
    public void addRecipe(@RequestBody AddRecipeRequest request) {
        Recipe recipe = new Recipe();
        List<Ingredient> ingredients = new ArrayList<>();
        for (Entry<String, Float> ingredient : request.getIngredients().entrySet()) {
            ingredients.add(Ingredient.builder()
                    .recipe(recipe)
                    .name(ingredient.getKey())
                    .weight(ingredient.getValue())
                .build());
        }
        recipe.setIngredients(ingredients);
        recipeRepository.save(recipe);
    }
}
