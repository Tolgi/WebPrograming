package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {


    @Id
    private String name;
    private Boolean spicy;
    private Float amount;
    private Boolean veggie;

    @JsonIgnore
    @ManyToMany(mappedBy = "pizzaIngredients", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Pizza> pizzaList;

}
