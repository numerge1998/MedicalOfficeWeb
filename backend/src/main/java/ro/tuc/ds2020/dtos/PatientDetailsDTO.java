package ro.tuc.ds2020.dtos;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;
import ro.tuc.ds2020.entities.Caregiver;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDetailsDTO {

    private UUID id_patient;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private Date birth_date;
    @NotNull
    private String gender;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER)
    private Caregiver caregiver;

    public PatientDetailsDTO() {
    }

    public PatientDetailsDTO( String name, String address, Date birth_date, String gender) {
        this.name = name;
        this.address = address;
        this.birth_date = birth_date;
        this.gender = gender;
    }


    public PatientDetailsDTO(UUID id, String name, String address, Date birth_date, String gender) {
        this.id_patient = id;
        this.name = name;
        this.address = address;
        this.birth_date = birth_date;
        this.gender = gender;
    }

    public UUID getId() {
        return id_patient;
    }

    public void setId(UUID id) {
        this.id_patient = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDetailsDTO that = (PatientDetailsDTO) o;
        return Objects.equals(id_patient, that.id_patient) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birth_date, that.birth_date) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_patient, name, address, birth_date, gender);
    }
}
