package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationDetailsDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlansDetailsDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;

public class MedicationPlanBuilder {

    private MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(), medicationPlan.getIntervals(), medicationPlan.getPeriod());
    }

    public static MedicationPlansDetailsDTO toMedicationPlansDetailsDTO(MedicationPlan medicationPlan) {
        return new MedicationPlansDetailsDTO(medicationPlan.getId(), medicationPlan.getIntervals(), medicationPlan.getPeriod());
    }

    public static MedicationPlan toEntity(MedicationPlansDetailsDTO medicationPlansDetailsDTO) {
        return new MedicationPlan(medicationPlansDetailsDTO.getIntervals(),
                medicationPlansDetailsDTO.getPeriod());
    }
}
