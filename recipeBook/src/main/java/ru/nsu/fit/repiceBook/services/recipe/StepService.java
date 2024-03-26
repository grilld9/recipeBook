package ru.nsu.fit.repiceBook.services.recipe;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepDTO;
import ru.nsu.fit.repiceBook.model.Image;

import java.util.List;

public interface StepService {

    /**
     * Создает шаг определенного рецепта
     * @param request данные запроса
     * @return данные о созданном шаге
     */
    StepDTO addStep(StepCreatingRequest request);

    /**
     * Получить шаг рецепта
     * @param recipeId id рецепта
     * @return данные шага
     */
    List<StepDTO> getSteps(Long recipeId);

    /**
     * Задать изображение шага
     * @param recipeId id рецепта
     * @param stepNumber номер шага
     * @param image изображение
     */
    void setStepImage(Long recipeId, Integer stepNumber, MultipartFile image);

    /**
     * Получить изображение шага
     * @param recipeId id рецепта
     * @param stepNumber номер шага
     * @return изображение
     */
    Image getStepImage(Long recipeId, Integer stepNumber);

    /**
     * Получить шаг рецепта
     * @param recipeId id рецепта
     * @param stepNumber номер шага
     * @return шаг
     */
    StepDTO getStep(Long recipeId, Integer stepNumber);
}
