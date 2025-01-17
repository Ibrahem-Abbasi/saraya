package saraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import saraya.util.JsonConverter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "`student-report`", schema = "saraya")
public class StudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @NotNull
    @Column(name = "student_id")
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @NotNull
    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Convert(converter = JsonConverter.class)
    @Column(name = "answers", nullable = false, columnDefinition = "json")
    @NotNull
    private List<String> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}