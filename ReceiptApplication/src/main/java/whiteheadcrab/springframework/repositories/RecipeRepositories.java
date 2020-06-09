package whiteheadcrab.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.domain.Recipe;

import java.util.Optional;

public interface RecipeRepositories extends CrudRepository<Recipe,Long>
{
    Optional<Recipe> findByDescription(String description);
}
