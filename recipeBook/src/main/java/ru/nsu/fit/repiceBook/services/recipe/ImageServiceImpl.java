package ru.nsu.fit.repiceBook.services.recipe;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.springframework.http.MediaType;
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
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(ImageIO.read(file.getInputStream()))
                .scalingMode(ScalingMode.BILINEAR)
                .scale(0.5)
                .outputFormat("jpg")
                .toOutputStream(outputStream);
            image.setImage(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("Ошибка при сжатии изображения");
            throw new UncheckedIOException(e);
        }
        image.setMediaType(MediaType.IMAGE_JPEG_VALUE);
        log.info("Изображение успешно сжато и сохранино");
        return imageRepository.save(image).getId();
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Изображение не найдено"));
    }


}
