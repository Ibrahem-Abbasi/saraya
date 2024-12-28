package saraya.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import saraya.entities.enums.Position;
import saraya.entities.enums.Profession;
import saraya.util.Views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", schema = "saraya")
public class User implements UserDetails {

    @JsonView(Views.BasicView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @JsonView(Views.BasicView.class)
    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @JsonView(Views.BasicView.class)
    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @Size(max = 60)
    @NotNull
    @Column(name = "hashed_password", nullable = false, length = 60)
    private String hashedPassword;

    @JsonView(Views.BasicView.class)
    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @JsonView(Views.BasicView.class)
    @NotNull
    @ColumnDefault("TEACHER")
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @JsonView(Views.BasicView.class)
    @Size(max = 45)
    @NotNull
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @JsonView(Views.BasicView.class)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "profession", nullable = false)
    private Profession profession;

    @JsonView(Views.BasicView.class)
    @CreationTimestamp
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @JsonView(Views.BasicView.class)
    @UpdateTimestamp
    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @JsonView(Views.BasicView.class)
    @NotNull
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = Boolean.TRUE;

    @JsonView(Views.DetailedView.class)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Group> groups = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(position.name()));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return getIsEnabled();
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDate.now();
    }
}