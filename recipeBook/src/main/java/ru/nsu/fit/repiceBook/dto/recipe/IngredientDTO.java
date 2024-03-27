package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.fit.repiceBook.model.enums.CountUnit;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IngredientDTO {
    private Long id;
    private String name;
    private Float weight;
    private CountUnit countUnit;
}
