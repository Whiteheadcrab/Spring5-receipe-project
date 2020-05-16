package whiteheadcrab.springframework.bootstrap;

import whiteheadcrab.springframework.domain.*;
import whiteheadcrab.springframework.repositories.CategoryRepository;
import whiteheadcrab.springframework.repositories.RecipeRepositories;
import whiteheadcrab.springframework.repositories.UnitofMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<Recipe> recipes = new ArrayList<>(2);

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

        //Adding recipe for Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        //Adding Notes for Guacamole
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        //Connecting Notes and Recipe of Guacamole
        guacRecipe.getCategories().add(american);
        guacRecipe.getCategories().add(mexican);

        //Adding to the return list
        recipes.add(guacRecipe);

        return recipes;
    }
}
