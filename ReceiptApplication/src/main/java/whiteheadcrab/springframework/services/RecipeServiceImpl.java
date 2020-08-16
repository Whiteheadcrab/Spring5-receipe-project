package whiteheadcrab.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.converters.RecipeCommandToRecipe;
import whiteheadcrab.springframework.converters.RecipeToCommandRecipe;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepositories recipeRepositories;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToCommandRecipe recipeToCommandRecipe;

    public RecipeServiceImpl(RecipeRepositories recipeRepositories, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToCommandRecipe recipeToCommandRecipe)
    {
        this.recipeRepositories = recipeRepositories;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToCommandRecipe = recipeToCommandRecipe;
    }


    @Override
    public Set<Recipe> getRecipes()
    {
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepositories.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    public Recipe findById(Long l)
    {
        Optional<Recipe> recipeOptional = recipeRepositories.findById(l);

        if (!recipeOptional.isPresent())
        {
            throw new RuntimeException("Recipe Not Found!");
        }

        return  recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToCommandRecipe.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepositories.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToCommandRecipe.convert(savedRecipe);
    }
}
