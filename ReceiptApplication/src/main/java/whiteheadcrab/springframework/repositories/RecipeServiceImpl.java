package whiteheadcrab.springframework.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
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
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepositories.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
