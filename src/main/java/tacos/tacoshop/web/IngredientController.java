package tacos.tacoshop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tacos.tacoshop.Ingredient;

@RestController
@RequestMapping(path="/ingredientsx", produces="application/json")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientService.fetchAll();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable int id){
            return ingredientService.getIngredientById(id);
    }


}
