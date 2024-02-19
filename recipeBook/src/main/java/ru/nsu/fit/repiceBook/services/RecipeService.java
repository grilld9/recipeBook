package ru.nsu.fit.repiceBook.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.dto.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;

@Service
@Slf4j
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeCreatingResponse createRecipe(RecipeCreatingRequest request) {
        //TODO
    }
}
