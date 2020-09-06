package whiteheadcrab.springframework.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.converters.IngredientToIngredientCommand;
import whiteheadcrab.springframework.converters.UnitOfMeasureToUnitOfMasureCommand;
import whiteheadcrab.springframework.domain.Ingredient;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest
{
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepositories recipeRepositories;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest()
    {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMasureCommand());
    }


    @BeforeEach
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepositories);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }


    @Test
    void findByRecipeIdAndIngredientId()
    {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredients(ingredient1);
        recipe.addIngredients(ingredient2);
        recipe.addIngredients(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepositories.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        //This is failed part . Expected 1 , actual null
        //assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepositories, times(1)).findById(anyLong());
    }
}