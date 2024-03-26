package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
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
    public ResponseEntity<StepDTO> addStep(@RequestBody StepCreatingRequest request) {
        return ResponseEntity.ok(stepService.addStep(request));
    }

    @GetMapping("{recipeId}/step/all")
    public ResponseEntity<List<StepDTO>> getSteps(@PathVariable Long recipeId) {
        return ResponseEntity.ok(stepService.getSteps(recipeId));
    }

    @GetMapping("{recipeId}/step/{stepNumber}")
    public ResponseEntity<StepDTO> getStep(@PathVariable Long recipeId,
                                           @PathVariable Integer stepNumber) {
        return ResponseEntity.ok(stepService.getStep(recipeId, stepNumber));
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
