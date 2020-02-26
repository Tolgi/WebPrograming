package mk.finki.ukim.mk.lab.model.staticdata;

import lombok.Data;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientsRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class DataHolder {

   public static List<Pizza> pizzas = new ArrayList<>();
   public static List<Order> orders = new ArrayList<>();
   public static List<Ingredient> ingredients = new ArrayList<>();
   public static Long counter;

   public final JpaIngredientsRepository jpaIngredientsRepository;
   public final JpaPizzaRepository jpaPizzaRepository;

    public DataHolder(JpaIngredientsRepository jpaIngredientsRepository, JpaPizzaRepository jpaPizzaRepository) {
        this.jpaIngredientsRepository = jpaIngredientsRepository;
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @PostConstruct
   public void init() {

       counter = 1L;

       pizzas.add(new Pizza("Margherita", "(tomato sauce, mozzarella)", new ArrayList<>(), false));
       pizzas.add(new Pizza("Carbonara", "(fresh cream, mozzarella, bacon)",new ArrayList<>(), true));
       pizzas.add(new Pizza("Vegetariana", "(tomato sauce, mushrooms)",new ArrayList<>(), true));
       pizzas.add(new Pizza("Calzone", "(Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)",new ArrayList<>(), false));
       pizzas.add(new Pizza("Cheddar", "(cheddar, tomato sauce)",new ArrayList<>(), true));
       pizzas.add(new Pizza("Capricciosa", "(tomato sauce, cheese, ham)",new ArrayList<>(), false));
       pizzas.add(new Pizza("Burger Classic", "(barbecue sauce, beef, mozzarella, onions)",new ArrayList<>(), true));
       pizzas.add(new Pizza("Burger Barbecue", "(ham, chicken meat, onions)",new ArrayList<>(), false));
       pizzas.add(new Pizza("Pepperoni", "(tomato sauce, mozzarella, sausage)",new ArrayList<>(), false));
       pizzas.add(new Pizza("Quattro Formaggi", "(Taleggio, Mascarpone, Gorgonzola, Parmesan)",new ArrayList<>(), true));


      Ingredient in1 = new Ingredient("mashroom", false, (float)30, true, new ArrayList<>());
      Ingredient in2 = new Ingredient("ham", false, (float)10, false, new ArrayList<>());
      Ingredient in3 = new Ingredient("peperoni", true, (float)20, false, new ArrayList<>());
      Ingredient in4 = new Ingredient("cheder", false, (float)50, true, new ArrayList<>());


      ingredients.add(in1);
      ingredients.add(in2);
      ingredients.add(in3);
      ingredients.add(in4);

      pizzas.get(0).addIngredients(in1);
      pizzas.get(1).addIngredients(in2);
      pizzas.get(2).addIngredients(in1);
      pizzas.get(3).addIngredients(in2);
      pizzas.get(4).addIngredients(in4);
      pizzas.get(5).addIngredients(in1);
      pizzas.get(6).addIngredients(in2);
      pizzas.get(7).addIngredients(in3);
      pizzas.get(8).addIngredients(in3);
      pizzas.get(9).addIngredients(in1);
      pizzas.get(1).addIngredients(in4);
      pizzas.get(2).addIngredients(in1);
      pizzas.get(1).addIngredients(in1);
      pizzas.get(5).addIngredients(in4);
      pizzas.get(7).addIngredients(in3);
      pizzas.get(5).addIngredients(in2);


      if(this.jpaPizzaRepository.count() == 0) {
          jpaIngredientsRepository.saveAll(ingredients);
          jpaPizzaRepository.saveAll(pizzas);
      }


   }

   public static Long getNextId(){ return counter++; }

}
