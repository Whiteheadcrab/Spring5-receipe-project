package whiteheadcrab.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.domain.Recipe;

@Component
public class RecipeComandToRecipe implements Converter<RecipeCommand, Recipe>
{
  //  public RecipeCommand convert(RecipeCommand recipeCommand)
}
