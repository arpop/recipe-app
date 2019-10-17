package pop.spring.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pop.spring.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
