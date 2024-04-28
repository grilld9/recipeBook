package ru.nsu.fit.repiceBook.services.recipe;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.Tag;

public interface RecipeService {

    /**
     * Создает основу рецепта, то есть сохраняет аттрибуты страницы рецепта: название, описание, фото,
     * шаги для каждого рецепта создаются отдельно
     * @param request Запрос на создание рецепта
     * @return Ответ на создание рецепта
     */
    RecipeDTO createRecipe(RecipeCreatingRequest request);

    /**
     * Получает рецепт по его ID
     * @param recipeId ID рецепта
     * @return рецепт
     */
    RecipeDTO getRecipe(Long recipeId);

    /**
     * Получает список рецептов текущего пользователя
     * @return список рецептов
     */
    List<RecipeDTO> getRecipesByUser();

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

    /**
     * Присвоить рецепту статус COMPLETED
     * @param recipeId id рецепта
     */
    void complete(Long recipeId);

    /**
     * Искать рецепты по названию
     * @param name название/часть названия рецепт
     * @param page номер страницы
     * @param pageSize развер страницы
     * @return
     */
    List<RecipeDTO> searchRecipes(String name, List<String> tags, Integer page, Integer pageSize);
}
