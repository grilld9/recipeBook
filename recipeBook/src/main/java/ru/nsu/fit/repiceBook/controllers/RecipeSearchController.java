package ru.nsu.fit.repiceBook.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.services.recipe.RecipeService;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/recipe/search")
@AllArgsConstructor
public class RecipeSearchController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> searchRecipes(@RequestParam String name, @RequestParam Integer page, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(recipeService.searchRecipes(name, page, pageSize));
    }
}
