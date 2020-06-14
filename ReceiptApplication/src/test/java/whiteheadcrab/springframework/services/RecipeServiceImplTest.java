package whiteheadcrab.springframework.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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