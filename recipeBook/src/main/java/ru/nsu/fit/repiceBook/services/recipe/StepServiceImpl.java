package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingResponse;
import ru.nsu.fit.repiceBook.model.Step;
import ru.nsu.fit.repiceBook.repositories.recipe.StepRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class StepServiceImpl implements StepService {

    private final ImageService imageService;
    private final RecipeService recipeService;
    private final StepRepository stepRepository;

    public StepCreatingResponse addStep(StepCreatingRequest request) {
        Step step = Step.builder()
                .recipe(recipeService.getRecipe(request.getRecipeId()))
                .name(request.getName())
                .number(request.getNumber())
                .description(request.getDescription())
                .imagePath(imageService.saveImage(request.getImage()).toString())
                .timerInSeconds(request.getTimerInSeconds())
                .build();
        stepRepository.save(step);
        log.info("Шаг рецепта {} сохранен", step.getRecipe().getName());
        return StepCreatingResponse.builder()
                .stepName(step.getName())
                .recipeName(step.getRecipe().getName())
                .stepNumber(step.getNumber())
                .build();
    }
}
