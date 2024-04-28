package ru.nsu.fit.repiceBook.services.recipe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.mappers.RecipeMapper;
import ru.nsu.fit.repiceBook.model.*;
import ru.nsu.fit.repiceBook.model.enums.RecipeStatus;

import ru.nsu.fit.repiceBook.repositories.recipe.RecipeRepository;
import ru.nsu.fit.repiceBook.repositories.recipe.TagRepository;
import ru.nsu.fit.repiceBook.services.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final IngredientsService ingredientsService;
    private final ImageService imageService;
    private final UserService userService;
    private final RecipeRepository recipeRepository;
    private final TagRepository tagRepository;
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
        List<Ingredient> ingredients = ingredientsService.saveIngredients(request.getIngredients()
                .stream()
                .map(ingredient -> mapper.map(ingredient, Ingredient.class))
                .toList(), recipe);
        log.info("Рецепт name=\"{}\" пользователя {} добавлен", request.getName(), user.getEmail());
        return RecipeMapper.toDTO(recipe, ingredients);
    }
    @Override
    public RecipeDTO getRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
        return RecipeMapper.toDTO(recipe);
    }

    @Override
    public List<RecipeDTO> getRecipesByUser() {
        return userService.getCurrUser().getRecipes()
                .stream()
                .map(RecipeMapper::toDTO)
                .toList();
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

    @Override
    @Transactional
    public void complete(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"));
        recipe.setStatus(RecipeStatus.COMPLETED);
        recipeRepository.save(recipe);
    }

    @Override
    public List<RecipeDTO> searchRecipes(String name, List<String> tagNames, Integer page, Integer pageSize) {
        List<Long> tagIds = tagNames.stream().map(tagName -> tagRepository.findByName(tagName).orElseThrow(
                () -> {
                    log.warn("Тег {} не найден", tagName);
                    return new NoSuchElementException("Тег " + tagName + " не найден");
                }
        ).getId()).toList();
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.searchRecipes(name, tagIds, pageable)
                .stream()
                .map(RecipeMapper::toDTO)
                .toList();
    }


}
