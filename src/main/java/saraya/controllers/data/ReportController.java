package saraya.controllers.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraya.entities.Question;
import saraya.entities.Report;
import saraya.services.data.QuestionService;
import saraya.services.data.ReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final QuestionService questionService;

    @Autowired
    public ReportController(ReportService reportService, QuestionService questionService) {
        this.reportService = reportService;
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Integer id) {
        return reportService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<Iterable<Question>> getQuestionsByReportId(@PathVariable("id") Integer id) {
        Optional<Report> report = reportService.getById(id);
        if (report.isPresent()) {
            List<Question> questions = new ArrayList<>();

            for (String strId : report.get().getQuestions()) {
                Integer numId = Integer.parseInt(strId);
                Optional<Question> question = questionService.getById(numId);
                question.ifPresent(questions::add);
            }
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Report> saveReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.save(report));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable("id") Integer id,
                                               @RequestBody Report report) {
        return ResponseEntity.ok(reportService.update(report, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable("id") Integer id) {
        try {
            reportService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
