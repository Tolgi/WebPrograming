package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.model.staticdata.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {

    @Override
    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(DataHolder.pizzas);
    }

    @Override
    public Pizza save(Pizza pizza) {
        this.findById(pizza.getName()).ifPresent(DataHolder.pizzas::remove);
        DataHolder.pizzas.add(pizza);
        return pizza;
    }

    @Override
    public Optional<Pizza> findById(String name) {
        return DataHolder.pizzas.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }
}
