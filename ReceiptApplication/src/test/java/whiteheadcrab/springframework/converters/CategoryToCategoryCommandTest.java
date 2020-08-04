package whiteheadcrab.springframework.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whiteheadcrab.springframework.commands.CategoryCommand;
import whiteheadcrab.springframework.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest
{
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand conveter;

    @BeforeEach
    public void setUp() throws Exception
    {
        conveter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = conveter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }

}