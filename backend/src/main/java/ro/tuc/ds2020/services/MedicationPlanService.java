package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDetailsDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlansDetailsDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, PatientRepository patientRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.patientRepository = patientRepository;
    }

    public List<MedicationPlanDTO> findMedicationPlans() {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findAll();
        return medicationPlanList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlansDetailsDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication Plan with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlansDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationPlansDetailsDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("MedicationPlan with id {} was inserted in db", medicationPlan.getId());
        return medicationPlan.getId();
    }

    public UUID delete(MedicationPlansDetailsDTO medicationPlanDTO) {
        medicationPlanRepository.deleteById(medicationPlanDTO.getId());
        LOGGER.debug("Medication plan with id {} was deleted from db", medicationPlanDTO.getId());
        return medicationPlanDTO.getId();
    }

    public UUID insert1(MedicationPlansDetailsDTO medicationPlanDTO, UUID patient_id) {
        Patient pa = patientRepository.getOne(patient_id);
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan.setPatient(pa);
        medicationPlanRepository.save(medicationPlan);
        return medicationPlan.getId();
    }

}
