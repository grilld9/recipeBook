package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeUpdateRequest {
    private String name;
    private String description;
    private List<String> tags;
}
