package pop.spring.recipeapp.domain;


import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    private BigDecimal amount;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(BigDecimal amount, String description, UnitOfMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.description = description;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Ingredient(BigDecimal amount, String description, UnitOfMeasure uom) {
        this.amount = amount;
        this.description = description;
        this.id = id;
        this.uom = uom;
    }

}
