package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class DoctorDTO extends RepresentationModel<DoctorDTO> {
    private UUID id_doctor;
    private String name;
    private int age;

    public DoctorDTO() {
    }

    public DoctorDTO(UUID id, String name, int age) {
        this.id_doctor = id;
        this.name = name;
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
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return age == doctorDTO.age &&
                Objects.equals(name, doctorDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
