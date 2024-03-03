package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.User;
import ru.nsu.fit.repiceBook.repositories.recipe.RecipeRepository;
import ru.nsu.fit.repiceBook.services.UserService;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final IngredientsService ingredientsService;
    private final ImageService imageService;
    private final UserService userService;
    private final RecipeRepository recipeRepository;

    @Value("${images.dir}")
    private String imagesDir;

    @Override
    public RecipeCreatingResponse createRecipe(RecipeCreatingRequest request) {
        User user = userService.getCurrUser();
        log.info("Добавление рецепта пользователю {}", user.getEmail());
        Recipe recipe = Recipe.builder()
            .name(request.getName())
            .user(user)
            .description(request.getDescription())
            .imagePath(imageService.saveImage(request.getImage()).toString())
            .build();
        ingredientsService.saveIngredients(request.getIngredients(), recipe);
        recipeRepository.save(recipe);
        log.info("Рецепт name=\"{}\" пользователя {} добавлен", request.getName(), user.getEmail());
        return RecipeCreatingResponse.builder()
                .isCreated(true)
                .build();
    }
    public Recipe getRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
    }

    public List<Recipe> getRecipesByUser() {
        return userService.getCurrUser().getRecipes();
    }

    public List<Recipe> getRecipesByUser(Long userId) {
        return userService.getUserById(userId).getRecipes();
    }
}
