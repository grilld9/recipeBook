package ru.nsu.fit.repiceBook.services.recipe;

import java.io.IOException;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;

public interface RecipeService {

    /**
     * Создает основу рецепта, то есть сохраняет аттрибуты страницы рецепта: название, описание, фото,
     * шаги для каждого рецепта создаются отдельно
     * @param request Запрос на создание рецепта
     * @return Ответ на создание рецепта
     * @throws IOException если возникла проблема при сохранении фотографии
     */
    RecipeCreatingResponse createRecipe(RecipeCreatingRequest request) throws IOException;
}
