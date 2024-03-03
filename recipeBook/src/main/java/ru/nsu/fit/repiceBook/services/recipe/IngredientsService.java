package ru.nsu.fit.repiceBook.services.recipe;

import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;

import java.util.List;
import java.util.NoSuchElementException;

public interface IngredientsService {

    /**
     * Проверяет доступны ли данные ингредиенты для использования, путем
     * сравнения их с предопределенными в приложении ингредиентами
     * @param ingredients список входящих ингредиентов
     * @throws NoSuchElementException если входящих ингредиентов нету среди
     * предопределнных ингредиентов
     */
    void saveIngredients(List<Ingredient> ingredients, Recipe recipe) throws NoSuchElementException;
}
