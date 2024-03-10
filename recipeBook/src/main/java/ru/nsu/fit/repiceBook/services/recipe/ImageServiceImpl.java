package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.services.repositories.ImageRepository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Long saveImage(MultipartFile file) {
        Image image = new Image();
        try {
            image.setImage(file.getBytes());
        } catch (IOException e) {
            log.error("Невозможно прочитать входящий файл: " + e.getMessage());
            throw new UncheckedIOException(e);
        }
        image.setMediaType(file.getContentType());
        return imageRepository.save(image).getId();
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Изображение не найдено"));
    }


}
