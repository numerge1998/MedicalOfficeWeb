package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(), caregiver.getName(), caregiver.getAddress(), caregiver.getBirth_date(), caregiver.getGender());
    }

    public static CaregiverDetailsDTO toCaregiverDetailsDTO(Caregiver caregiver) {
        return new CaregiverDetailsDTO(caregiver.getId(), caregiver.getName(), caregiver.getAddress(), caregiver.getBirth_date(), caregiver.getGender());
    }

    public static Caregiver toEntity(CaregiverDetailsDTO caregiverDetailsDTO) {
        return new Caregiver(caregiverDetailsDTO.getName(),
                caregiverDetailsDTO.getAddress(),
                caregiverDetailsDTO.getBirth_date(),
                caregiverDetailsDTO.getGender());
    }
}
