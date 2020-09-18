package whiteheadcrab.springframework.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.domain.Ingredient;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientToIngredientCommandTest
{
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1l);
    public static final Long UOM_ID = new Long(2l);

    IngredientToIngredientCommand converter;

    @BeforeEach
    public void setUp() throws Exception
    {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConverter() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConverterNullUOM() throws Exception
    {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientid(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUnitofMeasureCommand());
        assertEquals(ID_VALUE,ingredientCommand.getIngredientid());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
    }

    @Test
    public void testConverteWithUOM() throws Exception
    {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientid(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);

        //initializing UOM
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand.getUnitofMeasureCommand());
        assertEquals(ID_VALUE,ingredientCommand.getIngredientid());
        assertEquals(UOM_ID,ingredientCommand.getUnitofMeasureCommand().getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
    }
}