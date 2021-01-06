package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Medication  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_medication;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "side_effects", nullable = false)
    private String side_effects;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "medication_medication_plan",
                joinColumns = @JoinColumn(name = "id_medication"),
                inverseJoinColumns = @JoinColumn(name = "id_med_plan")
    )
    private List<MedicationPlan> medication_plans = new ArrayList<>();

    public void adMedicationPlans(MedicationPlan med){
        medication_plans.add(med);
        med.getMedications().add(this);
    }

    public void removeMedicationPlans(MedicationPlan med){
        medication_plans.remove(med);
        med.getMedications().remove(this);
    }

    public Medication() {
    }

    public Medication(String name, String side_effects, String dosage) {
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

    public String getDosage() {
        return dosage;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setName(String name) {
        this.name = name;
    }

}
