package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Student;
import saraya.repositories.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> getById(Integer id) {
        return studentRepository.findById(id);
    }

    public Iterable<Student> searchByName(String name) {
        return studentRepository.searchByName(name);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student, Integer id) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
