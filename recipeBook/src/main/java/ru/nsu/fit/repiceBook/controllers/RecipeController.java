package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;
import ru.nsu.fit.repiceBook.services.recipe.RecipeServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    private final RecipeServiceImpl recipeService;

    @PostMapping
    public ResponseEntity<RecipeCreatingResponse> addRecipe(@RequestBody RecipeCreatingRequest request) throws IOException {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }
}
