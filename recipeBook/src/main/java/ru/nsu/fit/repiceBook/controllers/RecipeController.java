package ru.nsu.fit.repiceBook.controllers;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;
import ru.nsu.fit.repiceBook.services.recipe.RecipeServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    private final RecipeServiceImpl recipeService;

    @GetMapping("/example")
    public RecipeCreatingRequest getCreatingRequestExample() {
        List<Ingredient> ingredients = new ArrayList<>();
        byte[] image = "SOME_IMAGE_DATA_FOR_EXAMPLE".getBytes();
        ingredients.add(Ingredient.builder().name("Лосось").weight(200F).build());
        ingredients.add(Ingredient.builder().name("Авокадо").weight(100F).build());
        return RecipeCreatingRequest.builder()
            .name("Макароны по флотски")
            .description("Очень вкусные макароны")
            .ingredients(ingredients)
            .image(image)
            .build();
    }

    @PostMapping
    public ResponseEntity<RecipeCreatingResponse> addRecipe(@RequestBody RecipeCreatingRequest request) throws IOException {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }
}
