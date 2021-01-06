package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {
    private UUID id_caregiver;
    private String name;
    private String address;
    private Date birthDate;
    private String gender;

    public CaregiverDTO() {
    }

    public CaregiverDTO(UUID id, String name, String address, Date birthDate, String gender) {
        this.id_caregiver = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public UUID getId() {
        return id_caregiver;
    }

    public void setId(UUID id) {
        this.id_caregiver = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth_date() {
        return birthDate;
    }

    public void setBirth_date(Date birth_date) {
        this.birthDate = birth_date;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaregiverDTO that = (CaregiverDTO) o;
        return Objects.equals(id_caregiver, that.id_caregiver) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_caregiver, name, birthDate, gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
