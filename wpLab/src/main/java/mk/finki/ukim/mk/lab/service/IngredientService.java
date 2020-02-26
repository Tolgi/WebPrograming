package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticdata.Page;


import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> listIngredients();
    Ingredient saveIngredient (String name, Boolean spicy, Float amount, Boolean veggie, String [] pizzaList);
    Ingredient editIngredient(String name, Boolean spicy, Float amount, Boolean veggie, String[] pizzaList);

    void deleteIngredient(String name);

    Page<Ingredient> getAllIngredients(int page, int size);
    Optional<Ingredient> getIngredientById(String name);
    List<Pizza> getAllPizzasByIngredientId(String name);
    public List<Ingredient> getAllIngredientBySpicy(Boolean spicy);
    List<Ingredient> getAllIngredients();
}
