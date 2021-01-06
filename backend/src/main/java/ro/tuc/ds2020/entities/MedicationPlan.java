package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name="medication_plan")
public class MedicationPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_med_plan;

    @Column(name = "intervals", nullable = false)
    private String intervals;

    @Column(name = "period", nullable = false)
    private String period;

    @ManyToMany(mappedBy = "medication_plans")
    private List<Medication> medications = new ArrayList<>();

    public Patient getPatient() {
        return patient;
    }

    public MedicationPlan setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public MedicationPlan setMedications(List<Medication> medications) {
        this.medications = medications;
        return this;
    }

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    public MedicationPlan() {
    }

    public MedicationPlan(String intervals, String period) {
        this.intervals = intervals;
        this.period = period;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public UUID getId() {
        return id_med_plan;
    }

    public void setId(UUID id) {
        this.id_med_plan = id;
    }


}
