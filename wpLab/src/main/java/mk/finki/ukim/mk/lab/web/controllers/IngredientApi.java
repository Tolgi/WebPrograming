package mk.finki.ukim.mk.lab.web.controllers;


import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticdata.Page;
import mk.finki.ukim.mk.lab.service.IngredientService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientApi {

    private final IngredientService ingredientService;

    public IngredientApi(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @PostMapping
    public Ingredient createIngredient(@RequestParam("name") String name,
                                       @RequestParam("spicy") Boolean spicy,
                                       @RequestParam("amount") Float amount,
                                       @RequestParam("veggie") Boolean veggie,
                                       @RequestParam(value = "pizzaList",required = false) String ... pizzaList){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA    "+ name+" "+spicy+" "+amount+" "+veggie);
        Ingredient ingredient = ingredientService.saveIngredient(name, spicy, amount, veggie, pizzaList);
        return ingredient;

    }

    @PatchMapping("/{ingId}")
    public Ingredient editIngredient(@PathVariable String ingId,
                                     @RequestParam Boolean spicy,
                                     @RequestParam Float amount,
                                     @RequestParam Boolean veggie,
                                     @RequestParam String ... pizzaList) {

        return ingredientService.editIngredient(ingId, spicy, amount, veggie, pizzaList);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        ingredientService.deleteIngredient(id);
    }

    @GetMapping("/{id}")
    public Optional<Ingredient> getIngredientById(@PathVariable String id){
        return this.ingredientService.getIngredientById(id);
    }


    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "1", required = false) int size) {
        return ingredientService.getAllIngredients(page, size);
    }

    @GetMapping("/{ingredientId}/pizzas")
    public List<Pizza> getAllPizzasByIngredientId(@PathVariable String ingredientId){
        return ingredientService.getAllPizzasByIngredientId(ingredientId);
    }


    @GetMapping("/attribute")
    public List<Ingredient> getAllIngredientBySpicy(@RequestParam(value = "spicy") Boolean spicy){
        return ingredientService.getAllIngredientBySpicy(spicy);
    }

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

}




