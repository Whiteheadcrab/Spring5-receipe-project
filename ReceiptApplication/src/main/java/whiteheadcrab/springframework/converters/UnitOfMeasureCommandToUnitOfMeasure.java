package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.UnitofMeasureCommand;
import whiteheadcrab.springframework.domain.UnitOfMeasure;


@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitofMeasureCommand, UnitOfMeasure>
{
    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitofMeasureCommand source)
    {
        if (source == null)
        {
            return null;
        }
        final UnitOfMeasure uom = new UnitOfMeasure();

        uom.setId(source.getId());
        uom.setDescription(source.getDescription());

        return uom;
    }
}
