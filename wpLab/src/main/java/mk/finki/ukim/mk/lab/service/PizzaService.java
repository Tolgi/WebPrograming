package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaService {

    List<Pizza> listPizzas();
    Pizza savePizza(String name, String decription, Boolean veggie, String [] ingredientList);
    Pizza editPizza(String name, String decription, Boolean veggie, String [] ingredientList);

    void deletePizza(String name);

    List<Pizza>getAllPizzas();
    Optional<Pizza> getPizzaById(String pizzaId);
    public List<Pizza> getAllPizzasWithLessIngr(Integer totalIngredients);
    public List<Ingredient> getTheSameIngredient(String pizzaId1, String pizzaId2);

}
