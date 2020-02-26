package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPizzaRepository extends JpaRepository<Pizza, String> {



    List<Pizza>findPizzasByPizzaIngredientsLessThan(Integer totalIngredients);
}
