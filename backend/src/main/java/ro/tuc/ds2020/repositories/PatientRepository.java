package ro.tuc.ds2020.repositories;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Patient p set p.name = :name, p.address = :address, p.birth_date = :birth_date, p.gender = :gender where p.id_patient = :id")
    void update(@Param("id") UUID id, @Param("name") String name, @Param("address") String address, @Param("birth_date") Date birth_date, @Param("gender") String gender);

    @Transactional
    @Modifying
    @Query(value = "insert into Patient (id_patient, address, birth_date, gender, name, caregiver_id_caregiver)" +
            " VALUES (:id_patient, :address, :birth_date, :gender, :name, :caregiver_id_caregiver)", nativeQuery = true) void myInsert (@Param("id_patient") UUID id_patinet, @Param("address") String address, @Param("birth_date") Date birth_date, @Param("gender") String gender, @Param("name") String name, @Param("caregiver_id_caregiver") UUID caregiver_id_caregiver);

    @Transactional
    @Modifying
    @Query(value ="UPDATE Patient p set p.caregiver_id_caregiver = :caregiver_id_caregiver where p.id_patient = :id", nativeQuery = true)
    void update1(@Param("id") UUID id, @Param("caregiver_id_caregiver") UUID caregiver_id_caregiver);

    @Transactional
    @Modifying
    @Query("Select p from MedicationPlan p join fetch p.patient c where c.id = :id")
    List<MedicationPlan> findPatientsMedPlans (@Param("id") UUID id);
}
