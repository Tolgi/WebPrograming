package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.InvalidCreationOfIngredient;
import mk.finki.ukim.mk.lab.exceptions.InvalidIngredientId;
import mk.finki.ukim.mk.lab.exceptions.InvalidIngredientName;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticdata.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private JpaPizzaRepository jpaPizzaRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, JpaPizzaRepository jpaPizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @Override
    public List<Ingredient> listIngredients() {
        return this.ingredientRepository.listIngredients();
    }

    @Override
    public Ingredient saveIngredient(String name, Boolean spicy, Float amount, Boolean veggie, String [] pizzaList) {

       Optional<Ingredient>  ingr = ingredientRepository.getIngredientById(name);
       if(ingr.isPresent()){
           throw new InvalidIngredientName();
       }

        if(ingredientRepository.getNumberOfSpicyIngredients() < 3 || spicy == false) {
            Ingredient ingredient = new Ingredient(name, spicy, amount, veggie, new ArrayList<>());

            if(pizzaList != null) {
                List<Pizza> pizzasListt = jpaPizzaRepository.findAllById(Arrays.asList(pizzaList));

                for (Pizza p : pizzasListt) {
                    p.addIngredients(ingredient);
                }
            }

            return this.ingredientRepository.saveIngredient(ingredient);

        }else{
            throw new InvalidCreationOfIngredient();
        }

    }


    @Override
    public Ingredient editIngredient(String name, Boolean spicy, Float amount, Boolean veggie, String[] pizzaList) {
        Ingredient ingredient = this.ingredientRepository.getIngredientById(name).orElseThrow(InvalidIngredientId::new);
        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);

        List<Pizza> oldPizzas = ingredient.getPizzaList();
        for(Pizza p : oldPizzas){
          p.getPizzaIngredients().remove(ingredient);
        }

        ingredient.setPizzaList(new ArrayList<>());
        List<Pizza> pizzasList = jpaPizzaRepository.findAllById(Arrays.asList(pizzaList));
        for(Pizza p : pizzasList){
            p.addIngredients(ingredient);
        }

        return this.ingredientRepository.saveIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        Ingredient ingredient = ingredientRepository.getIngredientById(name).orElseThrow(InvalidIngredientId::new);
        List<Pizza> oldPizzas = ingredient.getPizzaList();
        for(Pizza p : oldPizzas){
            p.getPizzaIngredients().remove(ingredient);
        }
        ingredient.setPizzaList(new ArrayList<>());
        ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.deleteIngredient(name);

    }


    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return this.ingredientRepository.getAll(page,size);
    }

    @Override
    public Optional<Ingredient> getIngredientById(String name) {
        return this.ingredientRepository.getIngredientById(name);
    }

    @Override
    public List<Pizza> getAllPizzasByIngredientId(String name) {
        Ingredient ingredient = ingredientRepository.getIngredientById(name).orElseThrow(InvalidIngredientId::new);
        return ingredient.getPizzaList();
    }

    @Override
    public List<Ingredient> getAllIngredientBySpicy(Boolean spicy) {
        List<Ingredient> allIngredient = ingredientRepository.getIngredientsBySpicy(spicy);

        return allIngredient;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.getIngredients();
    }
}
