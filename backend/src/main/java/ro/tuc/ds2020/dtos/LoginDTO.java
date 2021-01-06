package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class LoginDTO extends RepresentationModel<LoginDTO> {
    private UUID id_login;
    private String username;
    private String password;
    private String role;

    public LoginDTO() {
    }

    public LoginDTO(UUID id_login, String username, String password, String role) {
        this.id_login = id_login;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(id_login, loginDTO.id_login) &&
                Objects.equals(username, loginDTO.username) &&
                Objects.equals(password, loginDTO.password) &&
                Objects.equals(role, loginDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_login, username, password, role);
    }

    public UUID getId_login() {
        return id_login;
    }

    public LoginDTO setId_login(UUID id_login) {
        this.id_login = id_login;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
