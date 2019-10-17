package pop.spring.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pop.spring.recipeapp.domain.Category;


import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
