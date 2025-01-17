package saraya.repositories;

import org.springframework.data.repository.CrudRepository;
import saraya.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
