package tacos.tacoshop.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.tacoshop.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Integer> {
}
