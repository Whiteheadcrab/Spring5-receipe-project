package whiteheadcrab.springframework.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import whiteheadcrab.springframework.commands.UnitofMeasureCommand;
import whiteheadcrab.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import whiteheadcrab.springframework.domain.UnitOfMeasure;
import whiteheadcrab.springframework.repositories.UnitofMeasureRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest
{

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitofMeasureRepository unitofMeasureRepository;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitofMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms()
    {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitofMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitofMeasureCommand> commands = unitOfMeasureService.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitofMeasureRepository, times(1)).findAll();
    }
}