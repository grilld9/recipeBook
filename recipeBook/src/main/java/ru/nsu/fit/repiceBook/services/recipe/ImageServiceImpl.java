package ru.nsu.fit.repiceBook.services.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.Random;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.dir}")
    private String imagesDir;

    public Path saveImage(byte[] image) {
        try {
            if (!Files.exists(Paths.get(imagesDir))) {
                Files.createDirectories(Paths.get(imagesDir));
                log.info("Папка с изображениями создана: {}", imagesDir);
            }
            while(true) {
                try {
                    Path imagePath = Paths.get(imagesDir + "\\" + new Random().nextLong());
                    Files.write(imagePath, image, StandardOpenOption.CREATE_NEW);
                    log.info("Изображение загружено: {}", imagePath);
                    return imagePath;
                } catch (FileAlreadyExistsException e) {
                    // Повторяем пока не будет сгенерировано уникальное имя файла
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при загрузке изображения");
            throw new UncheckedIOException(e);
        }
    }
}
