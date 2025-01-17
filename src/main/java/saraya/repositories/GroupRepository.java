package saraya.repositories;

import saraya.entities.Group;
import org.springframework.data.repository.CrudRepository;
import saraya.entities.User;

public interface GroupRepository extends CrudRepository<Group, Integer> {
    Iterable<Group> findByTeacherId(Integer teacherId);
}
