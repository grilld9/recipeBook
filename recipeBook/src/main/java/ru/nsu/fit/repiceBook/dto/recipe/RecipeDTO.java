package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.fit.repiceBook.dto.UserDTO;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.enums.RecipeStatus;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private UserDTO userDTO;
    private Long imageId;
    private RecipeStatus status;
}
