package whiteheadcrab.springframework.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.converters.RecipeCommandToRecipe;
import whiteheadcrab.springframework.converters.RecipeToCommandRecipe;
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

    @Mock
    RecipeToCommandRecipe recipeToCommandRecipe;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        recipeServiceimpl = new RecipeServiceImpl(recipeRepositories,recipeCommandToRecipe,recipeToCommandRecipe);
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
    public void getRecipeCoomandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepositories.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToCommandRecipe.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeServiceimpl.findCommandById(1L);

        assertNotNull("Null recipe returned", commandById.toString());
        verify(recipeRepositories, times(1)).findById(anyLong());
        verify(recipeRepositories, never()).findAll();
    }

    @Test
    public void getRecipes() throws Exception
    {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeServiceimpl.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeServiceimpl.getRecipes();

        assertEquals(recipes.size(),1);

        verify(recipeRepositories,times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception
    {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeServiceimpl.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepositories, times(1)).deleteById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception
    {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //should go boom
    }
}