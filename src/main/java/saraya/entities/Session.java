package saraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session", schema = "saraya")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "number", columnDefinition = "int UNSIGNED not null")
    private Integer number;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    private Integer groupId;

    @Size(max = 255)
    @Column(name = "media_url")
    private String mediaUrl;

    @OneToMany(mappedBy = "sessionId")
    private List<SessionReport> sessionReport;

    @OneToMany(mappedBy = "sessionId", fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public List<SessionReport> getSessionReport() {
        return sessionReport;
    }
}