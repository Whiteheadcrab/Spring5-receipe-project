package whiteheadcrab.springframework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    private static IndexController indexController;

    @Mock
    private Model model;

    @Mock
    private static RecipeService recipeService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage()
    {
        //given
        Set<Recipe> recipeSet = new HashSet<>();
        //first Set
        recipeSet.add(new Recipe());

        //second Set
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipeSet.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        //then
        assertEquals("index",viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setIncontroller = argumentCaptor.getValue();
        assertEquals(2,setIncontroller.size());
    }
}