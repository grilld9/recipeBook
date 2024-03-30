package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.repositories.recipe.IngredientsRepository;
import ru.nsu.fit.repiceBook.repositories.recipe.SourceIngredientRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IngredientsService {

    private final SourceIngredientRepository sourceIngredientRepository;
    private final IngredientsRepository ingredientsRepository;

    @Override
    public List<Ingredient> saveIngredients(List<Ingredient> ingredients, Recipe recipe) throws NoSuchElementException {
        validateIngredients(ingredients);
        ingredients.forEach(ingredient -> ingredient.setRecipe(recipe));
        return ingredients.stream()
                .map(ingredientsRepository::save)
                .toList();
    }

    @Override
    public List<Ingredient> validateIngredients(List<Ingredient> ingredients) throws NoSuchElementException {
        for (Ingredient ingredient : ingredients) {
            if (!sourceIngredientRepository.existsByName(ingredient.getName())) {
                log.warn("Ингредиента {} не существует", ingredient.getName());
                throw new NoSuchElementException();
            }
        }
        log.info("Ингредиенты прошли проверку");
        return ingredients;
    }
}
