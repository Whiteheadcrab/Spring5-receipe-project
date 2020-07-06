package whiteheadcrab.springframework.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeServiceimpl;

    @Mock
    RecipeRepositories recipeRepositories;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        recipeServiceimpl = new RecipeServiceImpl(recipeRepositories);
    }

    @Test
    void getRecipeByIdTest() throws Exception
    {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepositories.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeServiceimpl.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned.getDescription());
        verify(recipeRepositories,times(1)).findById(anyLong());
        verify(recipeRepositories,never()).findAll();
    }

    @Test
    void getRecipes()
    {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeServiceimpl.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeServiceimpl.getRecipes();

        assertEquals(recipes.size(),1);

        verify(recipeRepositories,times(1)).findAll();
    }
}