package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Login;
import ro.tuc.ds2020.entities.MedicationPlan;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {

    @Query(value = "SELECT l " +
            "FROM Login l " +
            "WHERE l.username = :username")
    Optional<Login> findUserRole(@Param("username") String username);
}
