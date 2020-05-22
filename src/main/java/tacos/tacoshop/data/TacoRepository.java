package tacos.tacoshop.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacoshop.Taco;

public interface TacoRepository extends CrudRepository<Taco, Integer> {
}
