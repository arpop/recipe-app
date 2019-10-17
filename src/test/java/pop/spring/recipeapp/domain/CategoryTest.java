package pop.spring.recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;


    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getDescription() {
        String desc = "Pretty cat";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    public void getId() {
        Long id = 12L;
        category.setId(id);
        assertEquals(id, category.getId());
    }
}