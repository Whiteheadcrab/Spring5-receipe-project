package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.CategoryCommand;
import whiteheadcrab.springframework.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>
{
    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category)
    {
        if (category == null)
        {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setId(category.getId());

        return categoryCommand;
    }
}
