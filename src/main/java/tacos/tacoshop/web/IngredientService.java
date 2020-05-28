package tacos.tacoshop.web;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import tacos.tacoshop.Ingredient;
import tacos.tacoshop.data.IngredientRepository;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final RestTemplate restTemplate;
    private final IngredientRepository repo;
    private final String URL_STRING = "http://localhost:8080/ingredientsx";

    public Iterable<Ingredient> fetchAll() {
        return repo.findAll();
    }

    public Ingredient getIngredientById(@PathVariable int id){
        return restTemplate.getForObject(URL_STRING, Ingredient.class, id);
    }

}
