package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.InvalidPizzaId;
import mk.finki.ukim.mk.lab.exceptions.InvalidVeggieAttribute;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientsRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService{

    private final JpaPizzaRepository jpaPizzaRepository;
    private final JpaIngredientsRepository jpaIngredientsRepository;

    public PizzaServiceImpl(JpaPizzaRepository pizzaRepository, JpaIngredientsRepository jpaIngredientsRepository){
        this.jpaPizzaRepository = pizzaRepository;
        this.jpaIngredientsRepository = jpaIngredientsRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.jpaPizzaRepository.findAll();
    }

    @Override
    public Pizza savePizza(String name, String decription, Boolean veggie, String[] ingredientList) {

        List<Ingredient> ingrList = jpaIngredientsRepository.findAllById(Arrays.asList(ingredientList));
        veggie = isVeggie(ingrList);
        Pizza pizza = new Pizza(name, decription, ingrList, veggie);
        jpaPizzaRepository.save(pizza);
        return null;
    }

    public Boolean isVeggie(List<Ingredient>ingredientList){
        boolean firstItem = true;
        boolean isVegie = false;

        for(Ingredient i : ingredientList){
            if(firstItem){
                isVegie = i.getVeggie();
                firstItem = false;
            }

            if(i.getVeggie() != isVegie){
                throw new InvalidVeggieAttribute();
            }
        }

        return isVegie;
    }

    @Override
    public Pizza editPizza(String name, String decription, Boolean veggie, String[] ingredientList) {
        Pizza pizza = this.jpaPizzaRepository.findById(name).orElseThrow(InvalidPizzaId::new);
        pizza.setDescription(decription);
        pizza.setVeggie(veggie);

        List<Ingredient> ingrList = pizza.getPizzaIngredients();
        for(Ingredient ingredient : ingrList){
            ingredient.getPizzaList().remove(ingredient);
        }

        List<Ingredient> newIngrList = jpaIngredientsRepository.findAllById(Arrays.asList(ingredientList));
        pizza.setPizzaIngredients(newIngrList);

        return jpaPizzaRepository.save(pizza);
    }

    @Override
    public void deletePizza(String name) {
        jpaPizzaRepository.deleteById(name);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return jpaPizzaRepository.findAll();
    }

    @Override
    public Optional<Pizza> getPizzaById(String pizzaId) {
        return jpaPizzaRepository.findById(pizzaId);
    }

    @Override
    public List<Pizza> getAllPizzasWithLessIngr(Integer totalIngredients) {
        return this.jpaPizzaRepository.findPizzasByPizzaIngredientsLessThan(totalIngredients);
    }

    @Override
    public List<Ingredient> getTheSameIngredient(String pizzaId1, String pizzaId2) {
        Pizza pizza1 = jpaPizzaRepository.findById(pizzaId1).orElseThrow(InvalidPizzaId::new);
        Pizza pizza2 = jpaPizzaRepository.findById(pizzaId2).orElseThrow(InvalidPizzaId::new);
        List<Ingredient> pizzasList1 = pizza1.getPizzaIngredients();
        List<Ingredient> pizzasList2 = pizza2.getPizzaIngredients();

        List<Ingredient> sameIngredient = new ArrayList<>();
        for(Ingredient i : pizzasList1){
            if(pizzasList2.contains(i)){
                sameIngredient.add(i);
            }
        }
        return sameIngredient;
    }


}
