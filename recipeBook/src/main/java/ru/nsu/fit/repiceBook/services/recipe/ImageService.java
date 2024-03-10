package ru.nsu.fit.repiceBook.services.recipe;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.model.Image;

import java.nio.file.Path;

public interface ImageService {


    /**
     * Сохранить изображение
     * @param file файл изображения
     * @return id изображения в бд
     */
    Long saveImage(MultipartFile file);

    /**
     * Получить изображение
     * @param id изображения в базе данных
     * @return изображение
     */
    Image getImage(Long id);


}
