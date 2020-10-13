package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.domain.Category;
import whiteheadcrab.springframework.domain.Recipe;

@Component
public class RecipeToCommandRecipe implements Converter<Recipe, RecipeCommand>
{
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public RecipeToCommandRecipe(CategoryToCategoryCommand categoryToCategoryCommand ,
                                 NotesToNotesCommand notesToNotesCommand, IngredientToIngredientCommand
                                         ingredientToIngredientCommand)
    {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source)
    {
        if (source == null)
        {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0 )
        {
            source.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategoryCommands().add(categoryToCategoryCommand.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
        }

        return  recipeCommand;
    }
}
