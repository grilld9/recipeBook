package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeGetResponse;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.services.recipe.RecipeService;
import ru.nsu.fit.repiceBook.services.recipe.StepService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final StepService stepService;
    private final ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<?> postRecipe(
            @RequestBody RecipeCreatingRequest request) {
        recipeService.createRecipe(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getRecipes() {
        return ResponseEntity.ok(recipeService.getRecipesByUser());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> setRecipeImage(@PathVariable Long id,
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
}
