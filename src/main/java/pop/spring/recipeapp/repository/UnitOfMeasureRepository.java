package pop.spring.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pop.spring.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
