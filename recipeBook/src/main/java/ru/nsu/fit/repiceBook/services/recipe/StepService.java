package ru.nsu.fit.repiceBook.services.recipe;

import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingResponse;

public interface StepService {

    /**
     * Создает шаг определенного рецепта
     * @param request данные запроса
     * @return данные о созданном шаге
     */
    StepCreatingResponse addStep(StepCreatingRequest request);
}
