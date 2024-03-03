package ru.nsu.fit.repiceBook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.StepCreatingResponse;
import ru.nsu.fit.repiceBook.services.recipe.StepService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe/step")
public class StepController {

    private final StepService stepService;

    @PostMapping
    public ResponseEntity<StepCreatingResponse> addStep(@RequestBody StepCreatingRequest request) {
        return ResponseEntity.ok(stepService.addStep(request));
    }
}
