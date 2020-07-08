package whiteheadcrab.springframework.services;

import whiteheadcrab.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService
{
    Set<Recipe> getRecipes();

    Recipe findById(Long l);
}
