package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.dtos.LoginDTO;
import ro.tuc.ds2020.dtos.LoginDetailsDTO;
import ro.tuc.ds2020.services.DoctorService;
import ro.tuc.ds2020.services.LoginService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping()
    public ResponseEntity<List<LoginDTO>> getLogins() {
        List<LoginDTO> dtos = loginService.findLogin();
        for (LoginDTO dto : dtos) {
            Link personLink = linkTo(methodOn(LoginController.class)
                    .getLogin(dto.getId_login())).withRel("loginDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody LoginDetailsDTO loginDTO) {
        UUID loginID = loginService.insert(loginDTO);
        return new ResponseEntity<>(loginID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoginDetailsDTO> getLogin(@PathVariable("id") UUID loginId) {
        LoginDetailsDTO dto = loginService.findLoginById(loginId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{id}")
    public ResponseEntity<LoginDetailsDTO> getLoginRole(@PathVariable("id") String loginId) {
        LoginDetailsDTO dto = loginService.findUserRole(loginId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteLogin(@PathVariable("id") UUID loginId) {
        LoginDetailsDTO dto = loginService.findLoginById(loginId);
        UUID doctorID = loginService.delete(dto);
        return new ResponseEntity<>(doctorID, HttpStatus.OK);
    }

}
