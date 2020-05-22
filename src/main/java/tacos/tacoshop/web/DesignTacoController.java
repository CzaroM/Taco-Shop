package tacos.tacoshop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.tacoshop.Ingredient;
import tacos.tacoshop.Ingredient.Type;
import tacos.tacoshop.Order;
import tacos.tacoshop.Taco;
import tacos.tacoshop.User;
import tacos.tacoshop.data.IngredientRepository;
import tacos.tacoshop.data.TacoRepository;
import tacos.tacoshop.data.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal){
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient(1, "PSZENNA", Type.WRAP),
//                new Ingredient(2, "kukurydziana", Type.WRAP),
//                new Ingredient(3,"mielona wołowina", Type.PROTEIN),
//                new Ingredient(4,"kawałki mięsa", Type.PROTEIN),
//                new Ingredient(5,"pomidory", Type.VEGGIES),
//                new Ingredient(6,"Sałata", Type.VEGGIES),
//                new Ingredient(7,"cheddar", Type.CHEESE),
//                new Ingredient(8,"pikantny sos pomidorowy", Type.SAUCE),
//                new Ingredient(9,"smietana", Type.SAUCE));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i ->ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
//        model.addAttribute("design",new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco taco, Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepo.save(taco);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient>ingredientList, Type type){
        return ingredientList
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
