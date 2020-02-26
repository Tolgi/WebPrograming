package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticdata.Page;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    List<Ingredient> listIngredients();
    Ingredient saveIngredient (Ingredient ingredient);

    void deleteIngredient(String name);
    Page<Ingredient> getAll(int page, int size);
    Optional<Ingredient> getIngredientById(String name);

    List<Pizza> findAllPizzasById(List<String> pizzaNames);
    List<Ingredient> getIngredientsBySpicy(Boolean spicy);
    Integer getNumberOfSpicyIngredients();
    List<Ingredient> getIngredients();
}
