package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class MedicationDetailsDTO {

    private UUID id_medication;
    @NotNull
    private String name;
    @NotNull
    private String side_effects;
    @NotNull
    private String dosage;

    public MedicationDetailsDTO() {
    }

    public MedicationDetailsDTO( String name, String side_effects, String dosage) {
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
    }

    public MedicationDetailsDTO(UUID id, String name, String side_effects, String dosage) {
        this.id_medication = id;
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
    }

    public UUID getId() {
        return id_medication;
    }

    public void setId(UUID id) {
        this.id_medication = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDetailsDTO that = (MedicationDetailsDTO) o;
        return Objects.equals(id_medication, that.id_medication) &&
                Objects.equals(name, that.name) &&
                Objects.equals(side_effects, that.side_effects) &&
                Objects.equals(dosage, that.dosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_medication, name, side_effects, dosage);
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String sideEffects) {
        this.side_effects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setName(String name) {
        this.name = name;
    }

}
