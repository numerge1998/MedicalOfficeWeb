package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    List<MedicationPlan> findByPeriod(String period);

    /**
     * Example: Write Custom Query
     */
    @Query(value = "SELECT m " +
            "FROM MedicationPlan m " +
            "WHERE m.period = :period")
    Optional<MedicationPlan> findSeniorsByName(@Param("period") String period);

}
