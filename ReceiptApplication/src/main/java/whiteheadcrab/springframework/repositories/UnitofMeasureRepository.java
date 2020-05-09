package whiteheadcrab.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.domain.UnitOfMeasure;

public interface UnitofMeasureRepository extends CrudRepository<UnitOfMeasure,Long>
{
}
