package ru.nsu.fit.repiceBook.services.recipe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingResponse;
import ru.nsu.fit.repiceBook.dto.recipe.StepsGetRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepDTO;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.Step;
import ru.nsu.fit.repiceBook.services.repositories.recipe.StepRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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

    @Override
    public List<StepDTO> getSteps(StepsGetRequest request) {
        List<StepDTO> steps = new ArrayList<>();
        for (Step step : recipeService.getRecipe(request.getRecipeId()).getSteps()) {
            steps.add(StepDTO.builder()
                    .id(step.getId())
                    .name(step.getName())
                    .imageId(step.getImageId())
                    .description(step.getDescription())
                    .number(step.getNumber())
                    .timerInSeconds(step.getTimerInSeconds())
                    .build());
        }
        return steps;
    }

    @Override
    @Transactional
    public void setStepImage(Long recipeId, Integer stepNumber, MultipartFile image) {
        StepDTO stepDTO = getStep(recipeId, stepNumber);
        Step step = stepRepository.findById(stepDTO.getId()).get();
        step.setImageId(imageService.saveImage(image));
        log.info("Изображение шага {} рецепта {} сохранено", stepNumber, recipeId);
        stepRepository.save(step);
    }

    @Override
    public Image getStepImage(Long recipeId, Integer stepNumber) {
        return imageService.getImage(getStep(recipeId, stepNumber).getImageId());
    }

    @Override
    public StepDTO getStep(Long recipeId, Integer stepNumber) {
        return getSteps(new StepsGetRequest(recipeId))
                .stream()
                .filter(x -> x.getNumber().equals(stepNumber))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Шага " + stepNumber + " не существует"));
    }

}
