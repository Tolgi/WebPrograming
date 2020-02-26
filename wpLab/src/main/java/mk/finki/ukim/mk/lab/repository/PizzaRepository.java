package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository {

    List<Pizza> getAllPizzas();
    Pizza save(Pizza pizza);
    Optional<Pizza> findById(String name);

}
