package tacos.tacoshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tacos.tacoshop.data.IngredientRepository;
import tacos.tacoshop.Ingredient.Type;

@SpringBootApplication
public class TacoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient(1, "PSZENNA", Type.WRAP));
				repo.save(new Ingredient(2, "kukurydziana", Type.WRAP));
				repo.save(new Ingredient(3,"mielona wołowina", Type.PROTEIN));
				repo.save(new Ingredient(4,"kawałki mięsa", Type.PROTEIN));
				repo.save(new Ingredient(5,"pomidory", Type.VEGGIES));
				repo.save(new Ingredient(6,"Sałata", Type.VEGGIES));
				repo.save(new Ingredient(7,"cheddar", Type.CHEESE));
				repo.save(new Ingredient(8,"pikantny sos pomidorowy", Type.SAUCE));
				repo.save(new Ingredient(9,"smietana", Type.SAUCE));
			}
		};
	}
}
