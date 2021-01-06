package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Caregiver p set p.name = :name, p.address = :address, p.birthDate = :birthDate, p.gender = :gender where p.id_caregiver = :id")
    void update(@Param("id") UUID id, @Param("name") String name, @Param("address") String address, @Param("birthDate") Date birthDate, @Param("gender") String gender);

    @Transactional
    @Modifying
    @Query("Select p from Patient p join fetch p.caregiver c where c.id = :id")
    List<Patient> findCaregiverPatients (@Param("id") UUID id);

}
