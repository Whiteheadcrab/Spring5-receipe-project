package whiteheadcrab.springframework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import whiteheadcrab.springframework.services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

    private static IndexController indexController;

    @Mock
    private Model model;

    @Mock
    private static RecipeService recipeService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage()
    {
        String viewName = indexController.getIndexPage(model);

        assertEquals("index",viewName);

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),anySet());
    }
}