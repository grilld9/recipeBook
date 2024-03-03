package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepCreatingRequest {

    private Integer number;
    private Long recipeId;
    private String name;
    private String description;
    private int timerInSeconds;
    private byte[] image;
}
