package ru.nsu.fit.repiceBook.mappers;

import ru.nsu.fit.repiceBook.dto.UserDTO;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;

import java.util.List;

public class RecipeMapper {

    public static RecipeDTO toDTO(Recipe recipe) {
        return RecipeDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .userDTO(UserDTO.builder()
                        .id(recipe.getUser().getId())
                        .email(recipe.getUser().getEmail())
                        .build())
                .tags(recipe.getTags())
                .imageId(recipe.getImageId())
                .ingredients(recipe.getIngredients())
                .status(recipe.getStatus())
                .build();
    }

    public static RecipeDTO toDTO(Recipe recipe, List<Ingredient> ingredients) {
        return RecipeDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .userDTO(UserDTO.builder()
                        .id(recipe.getUser().getId())
                        .email(recipe.getUser().getEmail())
                        .build())
                .tags(recipe.getTags())
                .imageId(recipe.getImageId())
                .ingredients(ingredients)
                .status(recipe.getStatus())
                .build();
    }
}
