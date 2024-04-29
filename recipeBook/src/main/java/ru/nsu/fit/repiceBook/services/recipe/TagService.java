package ru.nsu.fit.repiceBook.services.recipe;

import ru.nsu.fit.repiceBook.dto.recipe.TagDTO;
import ru.nsu.fit.repiceBook.model.Tag;

import java.util.List;

public interface TagService {

    /**
     * Сохранить теги в базу данных
     * @param tags теги
     * @return список тегов
     */
    List<Tag> get(List<TagDTO> tags);
}
