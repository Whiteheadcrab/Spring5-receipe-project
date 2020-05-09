package whiteheadcrab.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>
{
}
