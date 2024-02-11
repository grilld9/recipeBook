package ru.nsu.fit.repiceBook.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
