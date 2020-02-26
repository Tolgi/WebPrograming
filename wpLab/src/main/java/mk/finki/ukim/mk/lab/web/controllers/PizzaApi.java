package mk.finki.ukim.mk.lab.web.controllers;


import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzaApi {

    private final PizzaService pizzaService;
    public PizzaApi(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public Pizza createPizza(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("veggie") Boolean veggie,
                             @RequestParam("ingredientList") String ... ingredientList){

        Pizza pizza = pizzaService.savePizza(name, description, veggie, ingredientList);
        return pizza;
    }

    @PatchMapping("/{pizzaId}")
    public Pizza editPizza(@PathVariable String pizzaId,
                           @RequestParam String description,
                           @RequestParam Boolean veggie,
                           @RequestParam String ... ingredientList) {

        return pizzaService.editPizza(pizzaId, description, veggie, ingredientList);
    }

    @DeleteMapping("/{pizzaId}")
    public void deletePizza(@PathVariable String pizzaId) {
        pizzaService.deletePizza(pizzaId);
    }

    @GetMapping
    public List<Pizza> getAllPizzas(){
        return this.pizzaService.getAllPizzas();
    }

    @GetMapping("/{pizzaId}")
    public Optional<Pizza> getPizzaById(@PathVariable String pizzaId){
        return this.pizzaService.getPizzaById(pizzaId);
    }

//------------------ DOPOLNITELNI ---------------------------------

//-----------ovaa ne raboti ------------
    @GetMapping("/less")
    public List<Pizza> getAllPizzasWithLessIngr(@RequestParam(value = "totalIngredients") Integer totalIngredients){
        return this.pizzaService.getAllPizzasWithLessIngr(totalIngredients);
    }
//-----------------------------------


    @GetMapping("/compare")
    public List<Ingredient> getTheSameIngredients(@RequestParam(value = "pizza1") String pizza1,
                                                @RequestParam(value = "pizza2") String pizza2){
        return this.pizzaService.getTheSameIngredient(pizza1, pizza2);
    }
}
