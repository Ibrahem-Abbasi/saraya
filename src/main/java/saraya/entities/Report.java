package saraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import saraya.util.JsonConverter;
import saraya.entities.enums.ConnectedTo;

import java.util.List;

@Entity
@Table(name = "report", schema = "saraya")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45, unique = true)
    private String name;

    @Convert(converter = JsonConverter.class)
    @Column(name = "questions", nullable = false, columnDefinition = "json")
    @NotNull
    private List<String> questions;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "connected_to", nullable = false)
    private ConnectedTo connectedTo;

    @Lob
    @Column(name = "description")
    private String description;

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

    public ConnectedTo getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(ConnectedTo connectedTo) {
        this.connectedTo = connectedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}