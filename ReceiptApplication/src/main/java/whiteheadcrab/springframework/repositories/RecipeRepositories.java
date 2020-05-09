package whiteheadcrab.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.domain.Recipe;

public interface RecipeRepositories extends CrudRepository<Recipe,Long>
{
}
