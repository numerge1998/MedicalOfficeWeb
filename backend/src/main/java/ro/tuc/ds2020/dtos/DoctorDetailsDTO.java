package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class DoctorDetailsDTO {

    private UUID id_doctor;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;


    public DoctorDetailsDTO() {
    }

    public DoctorDetailsDTO( String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public DoctorDetailsDTO(UUID id, String name, String address, int age) {
        this.id_doctor = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public UUID getId() {
        return id_doctor;
    }

    public void setId(UUID id) {
        this.id_doctor = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDetailsDTO that = (DoctorDetailsDTO) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}
