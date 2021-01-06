package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.dtos.LoginDTO;
import ro.tuc.ds2020.dtos.LoginDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.LoginBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Login;
import ro.tuc.ds2020.repositories.LoginRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<LoginDTO> findLogin() {
        List<Login> loginList = loginRepository.findAll();
        return loginList.stream()
                .map(LoginBuilder::toLoginDTO)
                .collect(Collectors.toList());
    }

    public LoginDetailsDTO findLoginById(UUID id) {
        Optional<Login> prosumerOptional = loginRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Login with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return LoginBuilder.toLoginDetailsDTO(prosumerOptional.get());
    }

    public LoginDetailsDTO findUserRole(String username) {
        Optional<Login> prosumerOptional = loginRepository.findUserRole(username);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Login with username {} was not found in db", username);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with username: " + username);
        }
        return LoginBuilder.toLoginDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(LoginDetailsDTO loginDTO) {
        Login login = LoginBuilder.toEntity(loginDTO);
        login = loginRepository.save(login);
        LOGGER.debug("Login with id {} was inserted in db", login.getId_login());
        return login.getId_login();
    }

    public UUID delete(LoginDetailsDTO loginDTO) {
        loginRepository.deleteById(loginDTO.getId_login());
        LOGGER.debug("Login plan with id {} was deleted from db", loginDTO.getId_login());
        return loginDTO.getId_login();
    }

}
