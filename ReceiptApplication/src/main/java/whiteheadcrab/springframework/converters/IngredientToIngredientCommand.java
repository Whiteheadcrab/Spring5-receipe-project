package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>
{
    private final UnitOfMeasureToUnitOfMasureCommand uomTouomc;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMasureCommand uomTouomc)
    {
        this.uomTouomc=uomTouomc;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient)
    {
        if (ingredient == null)
        {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setRecipeId(ingredient.getRecipeId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitofMeasureCommand(uomTouomc.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
