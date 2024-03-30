package ru.nsu.fit.repiceBook.services.recipe;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.repiceBook.model.Image;
import ru.nsu.fit.repiceBook.repositories.ImageRepository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile file) {
        Image image = new Image();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage buffImage = ImageIO.read(file.getInputStream());
            int width = buffImage.getWidth();
            int height = buffImage.getHeight();
            if (Math.max(width, height) > 600) {
                if (height > width) {
                    width = width * (1 - (height - 600) / height);
                    height = 600;
                } else {
                    height = width * (1 - (width - 600) / width);
                    width = 600;
                }
            }
            Thumbnails.of(ImageIO.read(file.getInputStream()))
                .scalingMode(ScalingMode.BILINEAR)
                .outputFormat("jpg")
                .height(height)
                .width(width)
                .toOutputStream(outputStream);
            image.setImage(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("Ошибка при сжатии изображения");
            throw new UncheckedIOException(e);
        }
        image.setMediaType(MediaType.IMAGE_JPEG_VALUE);
        log.info("Изображение успешно сжато и сохранино");
        return imageRepository.save(image);
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Изображение не найдено"));
    }
}
