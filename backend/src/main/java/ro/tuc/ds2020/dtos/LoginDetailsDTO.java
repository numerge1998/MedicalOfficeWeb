package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class LoginDetailsDTO {

    private UUID id_login;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;


    public LoginDetailsDTO() {
    }

    public LoginDetailsDTO( String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public LoginDetailsDTO(UUID id_login, String username, String password, String role) {
        this.id_login = id_login;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDetailsDTO that = (LoginDetailsDTO) o;
        return Objects.equals(id_login, that.id_login) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_login, username, password, role);
    }

    public UUID getId_login() {
        return id_login;
    }

    public LoginDetailsDTO setId_login(UUID id_login) {
        this.id_login = id_login;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginDetailsDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDetailsDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginDetailsDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
