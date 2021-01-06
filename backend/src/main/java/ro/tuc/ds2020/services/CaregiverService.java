package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDetailsDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(CaregiverDetailsDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db",caregiver.getId());
        return caregiver.getId();
    }

    public UUID update(CaregiverDetailsDTO caregiverDTO) {
        caregiverRepository.update(caregiverDTO.getId(), caregiverDTO.getName(), caregiverDTO.getAddress(), caregiverDTO.getBirth_date(), caregiverDTO.getGender());
        LOGGER.debug("Caregiver with id {} was updated from db", caregiverDTO.getId());
        return caregiverDTO.getId();
    }

    public UUID delete(CaregiverDetailsDTO caregiverDTO) {
        caregiverRepository.deleteById(caregiverDTO.getId());
        LOGGER.debug("Caregiver plan with id {} was deleted from db", caregiverDTO.getId());
        return caregiverDTO.getId();
    }

    public List<PatientDTO> findPatientsC(UUID id) {
        List<Patient> patientList = caregiverRepository.findCaregiverPatients(id);
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

}
