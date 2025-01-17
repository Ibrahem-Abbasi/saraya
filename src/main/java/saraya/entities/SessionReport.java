package saraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import saraya.util.JsonConverter;

import java.util.List;

@Entity
@Table(name = "`session-report`", schema = "saraya")
public class SessionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @NotNull
    @Column(name = "session_id")
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

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

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}