package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingResponse;
import ru.nsu.fit.repiceBook.dto.recipe.StepsGetRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepDTO;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.services.recipe.StepService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class StepController {

    private final StepService stepService;

    @PostMapping("/step")
    public ResponseEntity<StepCreatingResponse> addStep(@RequestBody StepCreatingRequest request) {
        return ResponseEntity.ok(stepService.addStep(request));
    }

    @GetMapping("step/all")
    public ResponseEntity<List<StepDTO>> getSteps(@RequestBody StepsGetRequest request) {
        return ResponseEntity.ok(stepService.getSteps(request));
    }

    @PostMapping("/{recipeId}/step/{number}/image")
    public ResponseEntity<?> setStepImage(@RequestBody MultipartFile image,
                                          @PathVariable("recipeId") Long recipeId,
                                          @PathVariable("number") Integer stepNumber) {
        stepService.setStepImage(recipeId, stepNumber, image);
        return ResponseEntity.ok("Изображение сохранено");
    }

    @GetMapping("/{recipeId}/step/{number}/image")
    public ResponseEntity<?> getStepImage(@PathVariable("recipeId") Long recipeId,
                                          @PathVariable("number") Integer stepNumber) {
        Image image = stepService.getStepImage(recipeId, stepNumber);
        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(image.getMediaType()))
            .body(image.getImage());
    }

}
