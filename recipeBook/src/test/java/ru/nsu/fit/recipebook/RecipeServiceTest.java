package ru.nsu.fit.recipebook;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.nsu.fit.repiceBook.RecipeBookApplication;
import ru.nsu.fit.repiceBook.dto.RegisterRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeCreatingRequest;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeDTO;
import ru.nsu.fit.repiceBook.dto.recipe.RecipeUpdateRequest;
import ru.nsu.fit.repiceBook.model.Tag;
import ru.nsu.fit.repiceBook.repositories.UserRepository;
import ru.nsu.fit.repiceBook.services.UserService;
import ru.nsu.fit.repiceBook.services.auth.AuthenticationService;
import ru.nsu.fit.repiceBook.services.recipe.RecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = RecipeBookApplication.class)
public class RecipeServiceTest {

    @Autowired
    public RecipeService recipeService;

    @Autowired
    public AuthenticationService authenticationService;

    @MockBean
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @BeforeEach
    public void init() {
        authenticationService.register(RegisterRequest.builder()
                .email("test@mail.ru")
                .password("testPassword")
                .build());
        Mockito.when(userService.getCurrUser()).thenReturn(userRepository.findByEmail("test@mail.ru").get());
    }

    @Test
    @Transactional
    public void updateTest() {
        RecipeDTO recipe = recipeService.createRecipe(RecipeCreatingRequest.builder()
                .name("testRecipeName")
                .description("testDescription")
                .ingredients(new ArrayList<>())
                .tags(new ArrayList<>())
                .build());
        recipeService.update(recipe.getId(),
                new RecipeUpdateRequest("updatedName", "updatedDesc", List.of("водка", "ром")));
        RecipeDTO updatedRecipe = recipeService.getRecipe(recipe.getId());
        Assertions.assertEquals("updatedName", updatedRecipe.getName());
        Assertions.assertEquals("updatedDesc", updatedRecipe.getDescription());
        Assertions.assertEquals(2, updatedRecipe.getTags().size());
        Assertions.assertTrue(updatedRecipe.getTags().stream().map(Tag::getName).allMatch(x -> Set.of("водка", "ром").contains(x)));
    }
}
