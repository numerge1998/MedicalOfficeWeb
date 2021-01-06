package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getAddress(), patient.getName(), patient.getBirth_date(), patient.getGender());
    }

    public static PatientDetailsDTO toPatientDetailsDTO(Patient patient) {
        return new PatientDetailsDTO(patient.getId(), patient.getName(), patient.getAddress(), patient.getBirth_date(), patient.getGender());
    }

    public static Patient toEntity(PatientDetailsDTO patientDetailsDTO) {
        return new Patient(patientDetailsDTO.getName(),
                patientDetailsDTO.getAddress(),
                patientDetailsDTO.getBirth_date(),
                patientDetailsDTO.getGender());
    }
}
