package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient>
{
    private final UnitOfMeasureCommandToUnitOfMeasure uomcTouom;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomcTouom)
    {
        this.uomcTouom = uomcTouom;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand)
    {
        if (ingredientCommand == null)
        {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUom(uomcTouom.convert(ingredientCommand.getUnitofMeasureCommand()));
        return ingredient;
    }
}
