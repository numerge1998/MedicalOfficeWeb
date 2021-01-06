package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlansDetailsDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication_plan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationPlanDTO>> getMedicationPlans() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlans();
        for (MedicationPlanDTO dto : dtos) {
            Link personLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getId())).withRel("medicationPlanDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationPlansDetailsDTO medicationPlanDTO) {
        UUID medicationPlanID =medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @PostMapping("/my_insert/{id}")
    public ResponseEntity<UUID> insertMedicationPlan1(@Valid @RequestBody MedicationPlansDetailsDTO medicationPlanDTO, @PathVariable("id") UUID medication_plan_id) {
        UUID medicationPlanID = medicationPlanService.insert1(medicationPlanDTO, medication_plan_id);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlansDetailsDTO> getMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        MedicationPlansDetailsDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        MedicationPlansDetailsDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanId);
        UUID medicationPlanID = medicationPlanService.delete(dto);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.OK);
    }

    //TODO: UPDATE per resource

}
