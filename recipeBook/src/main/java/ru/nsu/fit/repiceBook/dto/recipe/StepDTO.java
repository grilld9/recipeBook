package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepDTO {
    private Long id;
    private String name;
    private Integer number;
    private Long imageId;
    private String description;
    private Integer timerInSeconds;
}
