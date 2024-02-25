package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeCreatingResponse {
    private boolean isCreated;
}
