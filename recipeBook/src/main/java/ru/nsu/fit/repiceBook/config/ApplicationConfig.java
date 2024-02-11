package ru.nsu.fit.repiceBook.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import ru.nsu.fit.repiceBook.model.SourceIngredient;
import ru.nsu.fit.repiceBook.repositories.SourceIngredientRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
@Slf4j
public class ApplicationConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public CommandLineRunner ingredientsLoader(SourceIngredientRepository repo) {
    return args -> {
      try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ingredients.txt"))) {
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
}
