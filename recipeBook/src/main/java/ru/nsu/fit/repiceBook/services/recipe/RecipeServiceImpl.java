package ru.nsu.fit.repiceBook.services.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingResponse;
import ru.nsu.fit.repiceBook.model.Ingredient;
import ru.nsu.fit.repiceBook.model.Recipe;
import ru.nsu.fit.repiceBook.model.User;
import ru.nsu.fit.repiceBook.repositories.RecipeRepository;
import ru.nsu.fit.repiceBook.repositories.SourceIngredientRepository;
import ru.nsu.fit.repiceBook.repositories.UserRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final UserRepository userRepository;
    private final SourceIngredientRepository sourceIngredientRepository;

    @Value("${images.dir}")
    private String imagesDir;

    @Override
    public RecipeCreatingResponse createRecipe(RecipeCreatingRequest request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userEmail = authentication.getName();
        } else {
            throw new RuntimeException("Пользователь не авторизован");
        }
        User user = userRepository.findByEmail(userEmail).orElseThrow(
            () -> new NoSuchElementException("Пользователя " + userEmail + " не существует"));
        log.info("Добавление рецепта пользователю {}", user.getEmail());
        verifyIngredients(request.getIngredients());
        log.info("Ингредиенты прошли проверку");
        user.getRecipes().add(Recipe.builder()
            .name(request.getName())
            .description(request.getDescription())
            .imagePath(saveImage(request.getImage()).toString())
            .ingredients(request.getIngredients())
            .build());
        userRepository.save(user);
        log.info("Рецепт name=\"{}\" пользователя {} добавлен", request.getName(), user.getEmail());
        return RecipeCreatingResponse.builder()
                .isCreated(true)
                .build();
    }

    private void verifyIngredients(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!sourceIngredientRepository.existsByName(ingredient.getName())) {
                log.warn("Ингредиента {} не существует", ingredient.getName());
                throw new NoSuchElementException();
            }
        }
    }

    private Path saveImage(byte[] image) throws IOException {
        if (!Files.exists(Paths.get(imagesDir))) {
            Files.createDirectories(Paths.get(imagesDir));
            log.info("Папка с изображениями создана: {}", imagesDir);
        }
        Path imagePath = Paths.get(imagesDir + "\\" + new Random().nextLong());
        boolean isFileNameIsNotExist = false;
        while(!isFileNameIsNotExist) {
            try {
                Files.write(imagePath, image, StandardOpenOption.CREATE_NEW);
                log.info("Изображение загружено: {}", imagePath);
                isFileNameIsNotExist = true;
            } catch (FileAlreadyExistsException e) {
                //
            }
        }
        return imagePath;
    }
}
