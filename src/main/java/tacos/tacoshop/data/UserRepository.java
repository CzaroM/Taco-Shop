package tacos.tacoshop.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacoshop.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String Username);
}
