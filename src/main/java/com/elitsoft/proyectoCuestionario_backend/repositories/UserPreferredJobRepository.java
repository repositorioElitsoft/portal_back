package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.UserPreferredJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferredJobRepository extends JpaRepository<UserPreferredJob,Long> {
}
