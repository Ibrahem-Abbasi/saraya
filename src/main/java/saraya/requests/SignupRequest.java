package saraya.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import saraya.entities.enums.Position;
import saraya.entities.enums.Profession;

public class SignupRequest {

    @Size(max = 100)
    @NotNull
    private String email;

    @NotNull
    @Size(max = 24, min = 8)
    private String password;

    @Size(max = 100)
    @NotNull
    private String name;

    @Size(max = 45)
    @NotNull
    private String username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Position position;

    @Size(max = 45)
    @NotNull
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
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
}
