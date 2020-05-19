package whiteheadcrab.springframework.repositories;

import org.springframework.stereotype.Service;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepositories recipeRepositories;

    public RecipeServiceImpl(RecipeRepositories recipeRepositories)
    {
        this.recipeRepositories = recipeRepositories;
    }

    @Override
    public Set<Recipe> getRecipes()
    {
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepositories.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
