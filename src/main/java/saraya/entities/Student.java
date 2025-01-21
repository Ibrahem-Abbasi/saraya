package saraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import saraya.entities.enums.Gender;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "student", schema = "saraya")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    @Size(max = 45)
    @NotNull
    @Column(name = "mother_phone", nullable = false, length = 45)
    private String motherPhone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Size(max = 45)
    @Column(name = "other_area", length = 45)
    private String otherArea;

    @Size(max = 100)
    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Size(max = 45)
    @Column(name = "father_phone", length = 45)
    private String fatherPhone;

    @Size(max = 45)
    @Column(name = "other_phone", length = 45)
    private String otherPhone;

    @ColumnDefault("1")
    @Column(name = "still_active", columnDefinition = "tinyint UNSIGNED not null")
    private Boolean stillActive = Boolean.TRUE;

    @OneToMany(mappedBy = "studentId", fetch = FetchType.LAZY)
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @OneToMany(mappedBy = "studentId")
    private Set<StudentReport> studentReports = new LinkedHashSet<>();

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getOtherArea() {
        return otherArea;
    }

    public void setOtherArea(String otherArea) {
        this.otherArea = otherArea;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public Set<StudentReport> getStudentReports() {
        return studentReports;
    }

    public Boolean getStillActive() {
        return stillActive;
    }

    public void setStillActive(Boolean stillActive) {
        this.stillActive = stillActive;
    }
}