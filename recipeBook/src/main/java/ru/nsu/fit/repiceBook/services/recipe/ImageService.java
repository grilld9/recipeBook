package ru.nsu.fit.repiceBook.services.recipe;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.model.Image;

public interface ImageService {


    /**
     * Сохранить изображение
     * @param file файл изображения
     * @return сущность сохраненного изображения
     */
    Image saveImage(MultipartFile file);

    /**
     * Получить изображение
     * @param id изображения в базе данных
     * @return изображение
     */
    Image getImage(Long id);


}
