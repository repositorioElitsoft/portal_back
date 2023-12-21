package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.ObservationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationCategoryRepository extends JpaRepository<ObservationCategory, Long> {


}
