package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticdata.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {

    private final JpaIngredientsRepository jpaIngredientsRepository;
    private final JpaPizzaRepository jpaPizzaRepository;

    public IngredientRepositoryImpl(JpaIngredientsRepository jpaIngredientsRepository, JpaPizzaRepository jpaPizzaRepository) {
        this.jpaIngredientsRepository = jpaIngredientsRepository;
        this.jpaPizzaRepository = jpaPizzaRepository;
    }



    @Override
    public List<Ingredient> listIngredients() {
        return this.jpaIngredientsRepository.findAll();
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return this.jpaIngredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        this.jpaIngredientsRepository.deleteById(name);
    }

    @Override
    public Page<Ingredient> getAll(int page, int size) {
        org.springframework.data.domain.Page<Ingredient> result = this.jpaIngredientsRepository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public Optional<Ingredient> getIngredientById(String name) {
        return this.jpaIngredientsRepository.findById(name);
    }

    @Override
    public List<Pizza> findAllPizzasById(List<String> pizzaNames) {
        return this.jpaPizzaRepository.findAllById(pizzaNames);
    }

    @Override
    public List<Ingredient> getIngredientsBySpicy(Boolean spicy) {
     //   System.out.println((this.jpaIngredientsRepository.getIngredientsBySpicy(spicy)).toString());
        return this.jpaIngredientsRepository.getIngredientsBySpicy(spicy);
    }

    @Override
    public Integer getNumberOfSpicyIngredients() {
        return this.jpaIngredientsRepository.getNumberOfSpicyIngredients();
    }

    @Override
    public List<Ingredient> getIngredients() {
        return jpaIngredientsRepository.findAll();
    }


}
