package pop.spring.recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pop.spring.recipeapp.domain.*;
import pop.spring.recipeapp.repository.CategoryRepository;
import pop.spring.recipeapp.repository.RecipeRepository;
import pop.spring.recipeapp.repository.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        log.debug("Loading bootstrap data");
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Each");
        }

        Optional<UnitOfMeasure> tableSpoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Tablespoon");
        }

        Optional<UnitOfMeasure> teaSpoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Teaspoon");
        }

        Optional<UnitOfMeasure> dashSpoonOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashSpoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Dash");
        }

        Optional<UnitOfMeasure> pintSpoonOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintSpoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Pint");
        }
        Optional<UnitOfMeasure> cupSpoonOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupSpoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found: Cup");
        }

        UnitOfMeasure each = eachUomOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonOptional.get();
        UnitOfMeasure teaSpoon = teaSpoonOptional.get();
        UnitOfMeasure dash = dashSpoonOptional.get();
        UnitOfMeasure pint = pintSpoonOptional.get();
        UnitOfMeasure cups = cupSpoonOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found: American");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found: Mexican");
        }

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Just go on the website and read them from there. I don't have time to write them here");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("I should try to prepare this");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), "ripe avocados", each))
                .addIngredient(new Ingredient(BigDecimal.valueOf(5), "kosher salt", teaSpoon))
                .addIngredient(new Ingredient(BigDecimal.valueOf(2), "fresh lime juice or lemon juice", tableSpoon))
                .addIngredient(new Ingredient(BigDecimal.valueOf(2), "minced red onion or thinly sliced green onion", each))
                .addIngredient(new Ingredient(BigDecimal.valueOf(2), "serrano chiles, stesm and seeds removed, minced", each))
                .addIngredient(new Ingredient(BigDecimal.valueOf(2), "Cilantro", tableSpoon))
                .addIngredient(new Ingredient(BigDecimal.valueOf(2), "freshly grated black pepper", dash))
                .addIngredient(new Ingredient(BigDecimal.valueOf(5), "ripe tomato, seeds and pulp removed, chopped", each));

        guacRecipe.getCategories().add(americanCategoryOptional.get());
        guacRecipe.getCategories().add(mexicanCategoryOptional.get());

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy grilled chicken tacos");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("go on the website already");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("these must be very good");
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient(BigDecimal.valueOf(2), "ancho chili powder", tableSpoon))
                .addIngredient(new Ingredient(BigDecimal.ONE, "Dried oregano", teaSpoon))
                .addIngredient(new Ingredient(BigDecimal.ONE, "Dried cumin", teaSpoon))
                .addIngredient(new Ingredient(BigDecimal.ONE, "sugar", teaSpoon));
        // will stop with ingredients for time reasons
        tacosRecipe.getCategories().add(americanCategoryOptional.get());
        tacosRecipe.getCategories().add(mexicanCategoryOptional.get());

        recipes.add(guacRecipe);
        recipes.add(tacosRecipe);
        return recipes;

    }
}
