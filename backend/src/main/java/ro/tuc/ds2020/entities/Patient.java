package ro.tuc.ds2020.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Patient  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_patient;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false)
    private Date birth_date;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    private Caregiver caregiver;


    public Caregiver getCaregiver() {
        return caregiver;
    }

    public Patient setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
        return this;
    }

    public Patient() {
    }


    public Patient(String name, String address, Date birth_date, String gender) {
        this.name = name;
        this.address = address;
        this.birth_date = birth_date;
        this.gender = gender;
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

}
