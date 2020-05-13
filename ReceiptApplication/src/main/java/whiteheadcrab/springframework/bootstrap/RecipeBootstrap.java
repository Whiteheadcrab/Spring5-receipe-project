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

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitofMeasureRepository.findByDescription("Tablespoon");

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitofMeasureRepository.findByDescription("Teaspoon");

        Optional<UnitOfMeasure> dashUomOptional = unitofMeasureRepository.findByDescription("Dash");

        Optional<UnitOfMeasure> pintUomOptional = unitofMeasureRepository.findByDescription("Pint");

        Optional<UnitOfMeasure> cupUomOptional = unitofMeasureRepository.findByDescription("Cup");


        //By using kitchen
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        return null;
    }
}
