package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Person;

public class MedicationBuilder {

    private MedicationBuilder() {
    }

    public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(), medication.getName(), medication.getSide_effects(), medication.getDosage());
    }

    public static MedicationDetailsDTO toMedicationDetailsDTO(Medication medication) {
        return new MedicationDetailsDTO(medication.getId(), medication.getName(), medication.getSide_effects(), medication.getDosage());
    }

    public static Medication toEntity(MedicationDetailsDTO medicationDetailsDTO) {
        return new Medication(medicationDetailsDTO.getName(),
                medicationDetailsDTO.getSide_effects(),
                medicationDetailsDTO.getDosage());
    }
}
