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
    private final UnitOfMeasureToUnitOfMeasureCommand uomTouomc;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomTouomc)
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

        if (ingredient.getRecipe() != null)
        {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }

        ingredientCommand.setRecipeId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitofMeasureCommand(uomTouomc.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
