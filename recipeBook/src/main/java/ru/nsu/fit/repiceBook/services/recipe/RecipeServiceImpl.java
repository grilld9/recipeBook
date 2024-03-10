package ru.nsu.fit.repiceBook.services.recipe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.User;
import ru.nsu.fit.repiceBook.services.repositories.recipe.RecipeRepository;
import ru.nsu.fit.repiceBook.services.UserService;

import javax.naming.NoPermissionException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final IngredientsService ingredientsService;
    private final ImageService imageService;
    private final UserService userService;
    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe createRecipe(RecipeCreatingRequest request) {
        User user = userService.getCurrUser();
        log.info("Добавление рецепта пользователю {}", user.getEmail());
        Recipe recipe = Recipe.builder()
            .name(request.getName())
            .user(user)
            .description(request.getDescription())
            .build();
        recipeRepository.save(recipe);
        ingredientsService.saveIngredients(request.getIngredients(), recipe);
        log.info("Рецепт name=\"{}\" пользователя {} добавлен", request.getName(), user.getEmail());
        return recipe;
    }
    @Override
    public Recipe getRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
    }

    @Override
    public List<Recipe> getRecipesByUser() {
        return userService.getCurrUser().getRecipes();
    }

    @Override
    public List<Recipe> getRecipesByUser(Long userId) {
        return userService.getUserById(userId).getRecipes();
    }

    @Override
    @Transactional
    public void setRecipeImage(Long recipeId, MultipartFile image) {
        Recipe recipe = getRecipe(recipeId);
        checkPermissionToRecipe(recipe);
        recipe.setImageId(imageService.saveImage(image));
        recipeRepository.save(recipe);
    }

    @Override
    public Image getRecipeImage(Long recipeId) {
        return imageService.getImage(getRecipe(recipeId).getImageId());
    }

    @Override
    public void checkPermissionToRecipe(Recipe recipe) {
        User currUser = userService.getCurrUser();
        if (!Objects.equals(recipe.getUser().getId(), userService.getCurrUser().getId())) {
            log.warn("Пользователь {} не может редактировать рецепт с id={}", currUser.getEmail(), recipe.getId());
            throw new ResourceAccessException("Редактирование запрещено");
        }
    }
}
