package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pizzas")
public class Pizza {

    @Id
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> pizzaIngredients;

    private Boolean veggie;

    public void addIngredients(Ingredient ingredient){
        if(!this.getPizzaIngredients().contains(ingredient)) {
            this.pizzaIngredients.add(ingredient);
            ingredient.getPizzaList().add(this);
        }
    }
}
