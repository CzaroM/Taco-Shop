package tacos.tacoshop.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacoshop.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    Ingredient findById(int id);
}
