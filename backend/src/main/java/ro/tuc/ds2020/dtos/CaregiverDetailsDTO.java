package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDetailsDTO {

    private UUID id_caregiver;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private Date birthDate;
    @NotNull
    private String gender;

    public CaregiverDetailsDTO() {
    }

    public CaregiverDetailsDTO( String name, String address, Date birthDate, String gender) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public CaregiverDetailsDTO(UUID id, String name, String address, Date birthDate, String gender) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDetailsDTO that = (CaregiverDetailsDTO) o;
        return Objects.equals(id_caregiver, that.id_caregiver) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_caregiver, name, address, birthDate, gender);
    }
}
