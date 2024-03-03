package ru.nsu.fit.repiceBook.services.recipe;

import java.nio.file.Path;

public interface ImageService {

    /**
     * Сохраняет изображение в файловую систему. Имя файла будет определено с помощью случайным образом.
     * @param image изображение в виде массива байтов
     * @return путь к созданному файлу.
     */
    Path saveImage(byte[] image);
}
