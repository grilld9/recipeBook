package ru.nsu.fit.repiceBook.dto;

import lombok.Data;
import ru.nsu.fit.repiceBook.model.Ingredient;

import java.util.List;

@Data
public class RecipeCreatingRequest {

    private List<Ingredient> ingredients;
}
