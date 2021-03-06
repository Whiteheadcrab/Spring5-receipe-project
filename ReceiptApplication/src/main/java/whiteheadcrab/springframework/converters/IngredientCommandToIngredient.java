package whiteheadcrab.springframework.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.domain.Ingredient;
import whiteheadcrab.springframework.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient>
{
    private final UnitOfMeasureCommandToUnitOfMeasure uomcTouom;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomcTouom)
    {
        this.uomcTouom = uomcTouom;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source)
    {
        if (source == null)
        {
            return null;
        }

        final Ingredient ingredient = new Ingredient();

        ingredient.setId(source.getId());
        if(source.getRecipeId() != null)
        {
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredients(ingredient);
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomcTouom.convert(source.getUnitofMeasureCommand()));

        return ingredient;
    }
}
