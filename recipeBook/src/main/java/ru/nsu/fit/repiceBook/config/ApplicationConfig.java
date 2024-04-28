package ru.nsu.fit.repiceBook.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.fit.repiceBook.model.SourceIngredient;
import ru.nsu.fit.repiceBook.model.Tag;
import ru.nsu.fit.repiceBook.repositories.recipe.SourceIngredientRepository;
import ru.nsu.fit.repiceBook.repositories.recipe.TagRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

@Configuration
@Slf4j
public class ApplicationConfig {

  @Value("${application.ingredients.path}")
  private String sourceIngredientsPath;

  @Value("${application.tags.path}")
  private String sourceTagsPath;
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Gson gson() {
    return new Gson();
  }

  @Bean
  public CommandLineRunner ingredientsLoader(SourceIngredientRepository repo) {
    return args -> {
      try (BufferedReader reader = new BufferedReader(new FileReader(sourceIngredientsPath))) {
        String[] strings = reader.readLine().split(", ");
        for (String string : strings) {
            repo.save(SourceIngredient.builder().name(string).build());
        }
      } catch (IOException e) {
        log.error("Исходные ингредиенты не были загружены в БД: {}", e.getMessage());
      } catch (Exception e) {
        log.info("Исходные ингредиенты уже загружены в БД");
      }
    };
  }

  @Bean
  public CommandLineRunner tagsLoader(TagRepository tagRepository) {
    return args -> {
      Gson gson = new Gson();
      List<String> tagNames = gson.fromJson(Files.readString(Path.of(sourceTagsPath)), new TypeToken<>() {});
      List<Tag> tags = tagNames.stream()
              .map(tagName -> new Tag(null, tagName)).toList();
      tagRepository.saveAll(tags);
    };
  }
}
