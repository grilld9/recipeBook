package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.UserDTO;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.User;
import ru.nsu.fit.repiceBook.services.repositories.recipe.RecipeRepository;
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

    private final ModelMapper mapper;

    @Override
    public RecipeDTO createRecipe(RecipeCreatingRequest request) {
        User user = userService.getCurrUser();
        log.info("Добавление рецепта пользователю {}", user.getEmail());
        Recipe recipe = recipeRepository.save(Recipe.builder()
                .name(request.getName())
                .user(user)
                .description(request.getDescription())
                .build());
        List<Ingredient> ingredients = ingredientsService.saveIngredients(request.getIngredients(), recipe);
        log.info("Рецепт name=\"{}\" пользователя {} добавлен", request.getName(), user.getEmail());
        return RecipeDTO.builder()
                .id(recipe.getId())
                .ingredients(ingredients)
                .imageId(recipe.getImageId())
                .userDTO(mapper.map(user, UserDTO.class))
                .description(recipe.getDescription())
                .name(recipe.getName())
                .build();
    }
    @Override
    public RecipeDTO getRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
        return RecipeDTO.builder()
                .id(recipe.getId())
                .ingredients(recipe.getIngredients())
                .imageId(recipe.getImageId())
                .userDTO(mapper.map(recipe.getUser(), UserDTO.class))
                .description(recipe.getDescription())
                .name(recipe.getName())
                .build();
    }

    @Override
    public List<RecipeDTO> getRecipesByUser() {
        return userService.getCurrUser().getRecipes()
                .stream()
                .map(recipe -> mapper.map(recipe, RecipeDTO.class))
                .toList();
    }

    @Override
    public List<Recipe> getRecipesByUser(Long userId) {
        return userService.getUserById(userId).getRecipes();
    }

    @Override
    public void setRecipeImage(Long recipeId, MultipartFile image) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
        checkPermissionToRecipe(recipe);
        recipe.setImageId(imageService.saveImage(image).getId());
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
