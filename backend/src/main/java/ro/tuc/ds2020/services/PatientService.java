package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, CaregiverRepository caregiverRepository) {
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDetailsDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(PatientDetailsDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID insert1(PatientDetailsDTO patientDTO, UUID caregiver_id) {
        Caregiver pa = caregiverRepository.getOne(caregiver_id);
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient.setCaregiver(pa);
        patientRepository.save(patient);
        return patient.getId();
    }

    public UUID delete(PatientDetailsDTO patientDTO) {
        patientRepository.deleteById(patientDTO.getId());
        LOGGER.debug("Patient with id {} was deleted from db", patientDTO.getId());
        return patientDTO.getId();
    }

    public UUID update(PatientDetailsDTO patientDTO) {
        patientRepository.update(patientDTO.getId(), patientDTO.getName(), patientDTO.getAddress(), patientDTO.getBirth_date(), patientDTO.getGender());
        LOGGER.debug("Patient with id {} was updated from db", patientDTO.getId());
        return patientDTO.getId();
    }

    public List<MedicationPlanDTO> findPatientsMeds(UUID id) {
        List<MedicationPlan> medPlanList = patientRepository.findPatientsMedPlans(id);
        return medPlanList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

}
