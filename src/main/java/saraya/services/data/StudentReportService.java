package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.StudentReport;
import saraya.repositories.StudentReportRepository;

@Service
public class StudentReportService {
    private final StudentReportRepository studentReportRepository;

    @Autowired
    public StudentReportService(StudentReportRepository studentReportRepository) {
        this.studentReportRepository = studentReportRepository;
    }

    public StudentReport save(StudentReport studentReport) {
        return studentReportRepository.save(studentReport);
    }

    public StudentReport update(StudentReport studentReport, Integer id) {
        studentReport.setId(id);
        return studentReportRepository.save(studentReport);
    }

    public void delete(Integer id) {
        studentReportRepository.deleteById(id);
    }
}
