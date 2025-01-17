package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Question;
import saraya.repositories.QuestionRepository;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Iterable<Question> getAll() {
        return questionRepository.findAll();
    }

    public Optional<Question> getById(Integer id) {
        return questionRepository.findById(id);
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question update(Question question, Integer id) {
        question.setId(id);
        return questionRepository.save(question);
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }
}
