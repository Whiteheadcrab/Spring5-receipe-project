package whiteheadcrab.springframework.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.commands.UnitofMeasureCommand;
import whiteheadcrab.springframework.domain.Ingredient;
import whiteheadcrab.springframework.domain.Recipe;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientCommandToIngredientTest
{

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1l);
    public static final Long UOM_ID = new Long(2l);

    IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() throws Exception
    {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setIngredientid(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitofMeasureCommand unitOfMeasureCommand = new UnitofMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitofMeasureCommand(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getIngredientid());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setIngredientid(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitofMeasureCommand unitOfMeasureCommand = new UnitofMeasureCommand();


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getIngredientid());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }
}