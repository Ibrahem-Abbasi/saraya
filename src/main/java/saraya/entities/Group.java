package saraya.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import saraya.util.Views;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "`group`", schema = "saraya")
public class Group {

    @JsonView(Views.BasicView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @JsonView(Views.BasicView.class)
    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @JsonView(Views.BasicView.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @JsonView(Views.BasicView.class)
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonView(Views.BasicView.class)
    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;

    @JsonView(Views.BasicView.class)
    @Lob
    @Column(name = "description")
    private String description;

    @JsonView(Views.BasicView.class)
    @OneToMany(mappedBy = "groupId", fetch = FetchType.LAZY)
    @OrderBy("date ASC")
    private Set<Session> sessions = new LinkedHashSet<>();

    @JsonView(Views.BasicView.class)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Student> students = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public Set<Student> getStudents() {
        return students;
    }
}