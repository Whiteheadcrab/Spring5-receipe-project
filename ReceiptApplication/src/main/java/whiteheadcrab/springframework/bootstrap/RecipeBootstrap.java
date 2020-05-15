package whiteheadcrab.springframework.bootstrap;

import whiteheadcrab.springframework.domain.Category;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.domain.UnitOfMeasure;
import whiteheadcrab.springframework.repositories.CategoryRepository;
import whiteheadcrab.springframework.repositories.RecipeRepositories;
import whiteheadcrab.springframework.repositories.UnitofMeasureRepository;

import java.util.List;
import java.util.Optional;

public class RecipeBootstrap
{
    private final CategoryRepository categoryRepository;
    private final RecipeRepositories recipeRepositories;
    private final UnitofMeasureRepository unitofMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepositories recipeRepositories, UnitofMeasureRepository unitofMeasureRepository)
    {
        this.categoryRepository = categoryRepository;
        this.recipeRepositories = recipeRepositories;
        this.unitofMeasureRepository = unitofMeasureRepository;
    }

    private List<Recipe> getRecipes()
    {
        //By using tool
        Optional<UnitOfMeasure> eachUomOptional = unitofMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitofMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitofMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitofMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitofMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitofMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent())
        {
            throw new RuntimeException("Expected UOM NOT found");
        }

        //Set of get commands for tool category
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupUomOptional.get();


        //By using kitchen
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected Category NOT found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected Category NOT found");
        }

        Category american = americanCategoryOptional.get();
        Category mexican = mexicanCategoryOptional.get();

        return null;
    }
}
