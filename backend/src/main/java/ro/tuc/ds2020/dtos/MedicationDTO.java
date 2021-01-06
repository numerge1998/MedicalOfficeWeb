package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class MedicationDTO extends RepresentationModel<MedicationDTO> {
    private UUID id_medication;
    private String name;
    private String side_effects;
    private String dosage;

    public MedicationDTO() {
    }

    public MedicationDTO(UUID id, String name, String side_effects, String dosage) {
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

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedicationDTO that = (MedicationDTO) o;
        return Objects.equals(id_medication, that.id_medication) &&
                Objects.equals(name, that.name) &&
                Objects.equals(side_effects, that.side_effects) &&
                Objects.equals(dosage, that.dosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_medication, name, side_effects, dosage);
    }
}
