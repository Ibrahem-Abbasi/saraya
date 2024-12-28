package saraya.repositories;

import org.springframework.data.repository.CrudRepository;
import saraya.entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
