package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.UnitofMeasureCommand;
import whiteheadcrab.springframework.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitofMeasureCommand>
{
    @Synchronized
    @Nullable
    @Override
    public UnitofMeasureCommand convert(UnitOfMeasure unitOfMeasure)
    {
        if(unitOfMeasure != null)
        {
            final UnitofMeasureCommand uomc = new UnitofMeasureCommand();

            uomc.setId(unitOfMeasure.getId());
            uomc.setDescription(unitOfMeasure.getDescription());

            return uomc;
        }

        return null;
    }
}
