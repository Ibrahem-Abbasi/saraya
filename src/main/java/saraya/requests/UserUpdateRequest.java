package saraya.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import saraya.entities.enums.Profession;

public class UserUpdateRequest {

    @Size(max = 45)
    @NotNull
    private String username;

    @Size(max = 100)
    @NotNull
    private String name;

    @NotNull
    @Size(max = 24, min = 8)
    private String password;

    @Size(max = 45)
    @NotNull
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Profession profession;

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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
