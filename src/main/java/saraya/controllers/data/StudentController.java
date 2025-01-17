package saraya.controllers.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraya.entities.Student;
import saraya.entities.StudentReport;
import saraya.services.data.StudentReportService;
import saraya.services.data.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentReportService studentReportService;

    @Autowired
    public StudentController(StudentService studentService, StudentReportService studentReportService) {
        this.studentService = studentService;
        this.studentReportService = studentReportService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        return studentService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{input}")
    public ResponseEntity<Iterable<Student>> searchStudentByName(@PathVariable("input") String input) {
        return ResponseEntity.ok(studentService.searchByName(input));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                                 @PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.update(student, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        try {
            studentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ///// Report ///// //

    @PostMapping("/report")
    public ResponseEntity<StudentReport> saveReport(@RequestBody StudentReport report) {
        return ResponseEntity.ok(studentReportService.save(report));
    }

    @PutMapping("/report/{reportId}")
    public ResponseEntity<StudentReport> updateReport(@RequestBody StudentReport report,
                                                      @PathVariable("reportId") Integer reportId) {
        return ResponseEntity.ok(studentReportService.update(report, reportId));
    }

    @DeleteMapping("/report/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable("reportId") Integer reportId) {
        try {
            studentReportService.delete(reportId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
