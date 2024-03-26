package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepDTO;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.model.Step;
import ru.nsu.fit.repiceBook.services.repositories.recipe.RecipeRepository;
import ru.nsu.fit.repiceBook.services.repositories.recipe.StepRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StepServiceImpl implements StepService {

    private final ImageService imageService;
    private final StepRepository stepRepository;
    private final RecipeRepository recipeRepository;
    private final ModelMapper mapper;

    public StepDTO addStep(StepCreatingRequest request) {
        Step step = stepRepository.save(Step.builder()
                .recipe(recipeRepository.findById(request.getRecipeId()).orElseThrow(
                        () -> new NoSuchElementException("Рецепта с id=" + request.getRecipeId() + " не существует")))
                .name(request.getName())
                .number(request.getNumber())
                .description(request.getDescription())
                .timerInSeconds(request.getTimerInSeconds())
                .build());
        log.info("Шаг рецепта {} сохранен", step.getRecipe().getName());
        return mapper.map(step, StepDTO.class);

    }

    public List<StepDTO> getSteps(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(
                () -> new NoSuchElementException("Рецепта с id=" + recipeId + " не существует"))
                .getSteps().stream()
                .map(step -> mapper.map(step, StepDTO.class))
                .toList();
    }

    public void setStepImage(Long recipeId, Integer stepNumber, MultipartFile image) {
        StepDTO stepDTO = getStep(recipeId, stepNumber);
        Step step = stepRepository.findById(stepDTO.getId()).orElseThrow(
                () -> new NoSuchElementException("Шага " + stepNumber + " не существует"));
        step.setImageId(imageService.saveImage(image).getId());
        log.info("Изображение шага {} рецепта {} сохранено", stepNumber, recipeId);
        stepRepository.save(step);
    }

    public Image getStepImage(Long recipeId, Integer stepNumber) {
        return imageService.getImage(getStep(recipeId, stepNumber).getImageId());
    }

    public StepDTO getStep(Long recipeId, Integer stepNumber) {
        return getSteps(recipeId)
                .stream()
                .filter(x -> x.getNumber().equals(stepNumber))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Шага " + stepNumber + " не существует"));
    }
}
