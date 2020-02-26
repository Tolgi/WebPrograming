package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaIngredientsRepository extends JpaRepository<Ingredient, String> {


    @Query("select c from Ingredient c " +
            "WHERE c.spicy = :spicy ")
    List<Ingredient> getIngredientsBySpicy(Boolean spicy);

    @Query("select count(c.spicy) from Ingredient c " +
            "WHERE c.spicy = true")
    Integer getNumberOfSpicyIngredients();


}
