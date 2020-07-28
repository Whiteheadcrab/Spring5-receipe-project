package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>
{
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand)
    {
        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

        if(recipeCommand.getCategoryCommands() != null && recipeCommand.getCategoryCommands().size() > 0)
        {
            recipeCommand.getCategoryCommands()
                    .forEach( category -> recipeCommand.getCategoryCommands().add(categoryCommandToCategory.convert(category)));
        }

        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0)
        {
            recipeCommand.getIngredients()
                    .forEach( ingredient -> recipeCommand.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
        }

        return recipe;
    }
}
