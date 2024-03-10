package ru.nsu.fit.repiceBook.services.recipe;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Recipe;

public interface RecipeService {

    /**
     * Создает основу рецепта, то есть сохраняет аттрибуты страницы рецепта: название, описание, фото,
     * шаги для каждого рецепта создаются отдельно
     * @param request Запрос на создание рецепта
     * @return Ответ на создание рецепта
     */
    Recipe createRecipe(RecipeCreatingRequest request);

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

    /**
     * Изменить изображение рецепта
     * @param recipeId id рецепта
     * @param image изображение
     */
    void setRecipeImage(Long recipeId, MultipartFile image);

    /**
     * Получить изображение рецепта
     * @param recipeId id рецепта
     * @return изображение рецепта
     */
    Image getRecipeImage(Long recipeId);

    /**
     * Проверить есть ли у текущего польхователя права на редактирование рецепта
     * @param recipe рецепт
     */
    void checkPermissionToRecipe(Recipe recipe);
}
