package ru.nsu.fit.repiceBook.dto.recipe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.nsu.fit.repiceBook.model.Ingredient;

import java.util.List;

@Data
@Builder
public class RecipeCreatingRequest {

    @NotNull(message="Название рецепта не может быть пустым")
    private String name;
    private String description;
    @NotNull
    @Size(min=1, message="Должен быть хотя бы 1 ингредиент")
    private List<Ingredient> ingredients;
}
