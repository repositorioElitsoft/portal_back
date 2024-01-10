package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.ObservationUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObservationUpdateRepository extends JpaRepository<ObservationUpdate, Long> {

    @Query("SELECT o FROM ObservationUpdate o WHERE o.observation.id = :id AND o.updatedAt = (SELECT MAX(ee.updatedAt) FROM ObservationUpdate ee WHERE ee.observation.id = :id)")
    Optional<ObservationUpdate> findMostRecentRecord(Long id);

    // Custom query to get the record with the first date in existence
    @Query("SELECT o FROM ObservationUpdate o WHERE o.observation.id = :id AND o.updatedAt = (SELECT MIN(ee.updatedAt) FROM ObservationUpdate ee WHERE ee.observation.id = :id)")
    Optional<ObservationUpdate> findFirstRecord(Long id);
}
