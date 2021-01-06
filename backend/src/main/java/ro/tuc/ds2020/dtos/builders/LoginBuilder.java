package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Login;
import ro.tuc.ds2020.entities.Person;

public class LoginBuilder {

    private LoginBuilder() {
    }

    public static LoginDTO toLoginDTO(Login login) {
        return new LoginDTO(login.getId_login(), login.getUsername(), login.getPassword(), login.getRole());
    }

    public static LoginDetailsDTO toLoginDetailsDTO(Login login) {
        return new LoginDetailsDTO(login.getId_login(), login.getUsername(), login.getPassword(), login.getRole());
    }

    public static Login toEntity(LoginDetailsDTO loginDetailsDTO) {
        return new Login(loginDetailsDTO.getUsername(),
                loginDetailsDTO.getPassword(),
                loginDetailsDTO.getRole());
    }
}
