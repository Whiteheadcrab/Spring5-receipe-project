package whiteheadcrab.springframework.services;

import whiteheadcrab.springframework.commands.IngredientCommand;

public interface IngredientService
{
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    IngredientCommand deleteIngredientCommand(Long recipeId, Long ingredientId);
}
