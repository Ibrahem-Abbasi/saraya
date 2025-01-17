package saraya.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import saraya.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.name LIKE CONCAT('%', :name, '%')")
    Iterable<Student> searchByName(@Param("name") String name);
}
