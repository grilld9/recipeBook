package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.fit.repiceBook.model.Ingredient;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RecipeGetResponse {

    private Long id;
    private String name;
    private String description;
    private Long imageId;
    private Long userId;
    private List<Ingredient> ingredients;
}
