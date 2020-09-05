package whiteheadcrab.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whiteheadcrab.springframework.commands.IngredientCommand;
import whiteheadcrab.springframework.converters.IngredientToIngredientCommand;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService
{
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepositories recipeRepositories;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepositories recipeRepositories)
    {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepositories = recipeRepositories;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepositories.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
