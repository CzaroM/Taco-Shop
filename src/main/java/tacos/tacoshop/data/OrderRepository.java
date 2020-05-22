package tacos.tacoshop.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacoshop.Order;
import tacos.tacoshop.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUserOrderByPlacedAtDesc(User user);
}
