package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.services.recipe.RecipeService;
import ru.nsu.fit.repiceBook.services.recipe.StepService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDTO> postRecipe(
            @RequestBody RecipeCreatingRequest request) {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }


    @GetMapping("/my")
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        return ResponseEntity.ok(recipeService.getRecipesByUser());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<String> setRecipeImage(@PathVariable Long id,
            @RequestBody MultipartFile image) {
        recipeService.setRecipeImage(id, image);
        return ResponseEntity.ok("Изображение изменено");
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getRecipeImage(@PathVariable Long id) {
        Image image = recipeService.getRecipeImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getMediaType()))
                .body(image.getImage());
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeRecipe(@PathVariable Long id) {
        recipeService.complete(id);
        return ResponseEntity.ok("Рецепт завершен");
    }
}
