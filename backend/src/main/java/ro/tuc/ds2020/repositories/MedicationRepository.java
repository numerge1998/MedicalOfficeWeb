package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Medication;

import javax.transaction.Transactional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Medication p set p.name = :name, p.side_effects = :side_effects, p.dosage = :dosage where p.id_medication = :id")
    void update(@Param("id") UUID id, @Param("name") String name, @Param("side_effects") String side_effects, @Param("dosage") String dosage);


}
