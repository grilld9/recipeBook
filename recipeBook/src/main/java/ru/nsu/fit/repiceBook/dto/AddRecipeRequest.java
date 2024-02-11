package ru.nsu.fit.repiceBook.dto;

import java.util.Map;
import lombok.Data;

@Data
public class AddRecipeRequest {

    private Map<String, Float> ingredients;


}
