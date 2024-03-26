package ru.nsu.fit.repiceBook.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepDTO {
    private Long id;
    private String name;
    private Integer number;
    private Long imageId;
    private String description;
    private Integer timerInSeconds;
}
