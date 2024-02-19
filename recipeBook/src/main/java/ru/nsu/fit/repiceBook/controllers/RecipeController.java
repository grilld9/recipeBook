package ru.nsu.fit.repiceBook.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.repiceBook.dto.AddRecipeRequest;
import ru.nsu.fit.repiceBook.dto.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;
import ru.nsu.fit.repiceBook.services.RecipeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeCreatingResponse> addRecipe(@RequestBody RecipeCreatingRequest request) {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }
}
