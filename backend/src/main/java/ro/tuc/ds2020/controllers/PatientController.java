package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody PatientDetailsDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @PostMapping("/my_insert/{id}")
    public ResponseEntity<UUID> insertPatient1(@Valid @RequestBody PatientDetailsDTO patientDTO, @PathVariable("id") UUID caregiver_id) {
        UUID patientID = patientService.insert1(patientDTO, caregiver_id);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDetailsDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDetailsDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UUID> updatePatient(@Valid @RequestBody PatientDetailsDTO patientDTO) {
        UUID patientID = patientService.update(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deletePatient(@PathVariable("id") UUID patientId) {
        PatientDetailsDTO dto = patientService.findPatientById(patientId);
        UUID patientID = patientService.delete(dto);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }

    @GetMapping("/find_med_plan/{id}")
    public ResponseEntity<List<MedicationPlanDTO>> getMedPlans(@PathVariable("id") UUID patientId) {
        List<MedicationPlanDTO> dtos = patientService.findPatientsMeds(patientId);
        for (MedicationPlanDTO dto : dtos) {
            Link personLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getId())).withRel("medicationPlanDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
