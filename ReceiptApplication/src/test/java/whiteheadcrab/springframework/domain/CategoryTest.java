package whiteheadcrab.springframework.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    private static Category category;

    @BeforeAll
    static void SetUp()
    {
        category = new Category();
    }

    @Test
    public void getId()
    {
        Long idValue = 4l;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDescription()
    {
        String descriptionValue = "Description for test";
        category.setDescription(descriptionValue);
        assertEquals(descriptionValue,category.getDescription());
    }

    @Test
    public void getRecipes()
    {
        Set<Recipe> recipeSet = new HashSet<>();
        category.setRecipes(recipeSet);
        assertEquals(recipeSet,category.getRecipes());
    }
}