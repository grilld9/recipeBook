package ru.nsu.fit.repiceBook.services.recipe;

import java.util.List;

import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Recipe;

public interface RecipeService {

    /**
     * Создает основу рецепта, то есть сохраняет аттрибуты страницы рецепта: название, описание, фото,
     * шаги для каждого рецепта создаются отдельно
     * @param request Запрос на создание рецепта
     * @return Ответ на создание рецепта
     */
    RecipeCreatingResponse createRecipe(RecipeCreatingRequest request);

    /**
     * Получает рецепт по его ID
     * @param recipeId ID рецепта
     * @return рецепт
     */
    Recipe getRecipe(Long recipeId);

    /**
     * Получает список рецептов текущего пользователя
     * @return список рецептов
     */
    List<Recipe> getRecipesByUser();

    /**
     * Получает список рецептов определенного пользователя
     * @param userId ID пользователя
     * @return список рецептов
     */
    List<Recipe> getRecipesByUser(Long userId);
}
