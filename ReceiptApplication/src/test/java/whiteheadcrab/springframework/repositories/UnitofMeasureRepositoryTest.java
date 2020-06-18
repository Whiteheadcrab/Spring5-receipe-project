package whiteheadcrab.springframework.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import whiteheadcrab.springframework.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UnitofMeasureRepositoryTest {

    @Autowired
    UnitofMeasureRepository unitofMeasureRepository;

    @BeforeEach
    public void setUp()
    {

    }

    @Test
    public void findByDescription()
    {
        Optional<UnitOfMeasure> uomOptional = unitofMeasureRepository.findByDescription("Tablespoon");

        assertEquals("Tablespoon" , uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup()
    {
        Optional<UnitOfMeasure> uomOptional = unitofMeasureRepository.findByDescription("Cup");

        assertEquals("Cup" , uomOptional.get().getDescription());
    }
}