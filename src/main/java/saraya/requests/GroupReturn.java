package saraya.requests;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import saraya.entities.Group;
import saraya.entities.Session;
import saraya.entities.Student;
import saraya.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GroupReturn {

    @NotNull
    private Integer id;

    @Size(max = 45)
    @NotNull
    private String name;

    @NotNull
    private User teacher;

    @NotNull
    private LocalDate startDate;

    private LocalDate finishDate;

    @Lob
    private String description;

    private final List<Session> sessions = new ArrayList<>();

    private final Set<Student> students = new LinkedHashSet<>();

    public GroupReturn() {}

    public GroupReturn(Group group, User teacher) {
        this.id = group.getId();
        this.name = group.getName();
        this.startDate = group.getStartDate();
        this.finishDate = group.getFinishDate();
        this.description = group.getDescription();
        this.students.addAll(group.getStudents());
        this.sessions.addAll(group.getSessions());
        this.teacher = teacher;
    }

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

    public List<Session> getSessions() {
        return sessions;
    }

    public Set<Student> getStudents() {
        return students;
    }
}
