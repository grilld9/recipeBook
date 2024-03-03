package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepCreatingResponse {

    private String recipeName;
    private String stepName;
    private Integer stepNumber;
}
