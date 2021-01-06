package ro.tuc.ds2020.dtos;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Caregiver;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDTO extends RepresentationModel<PatientDTO> {
    private UUID id_patient;
    private String name;
    private String address;
    private Date birth_date;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER)
    private Caregiver caregiver;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String gender;

    public PatientDTO() {
    }

    public PatientDTO(UUID id,String address, String name, Date birth_date, String gender) {
        this.id_patient = id;
        this.address = address;
        this.name = name;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(id_patient, that.id_patient) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birth_date, that.birth_date) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_patient, name, birth_date, gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
