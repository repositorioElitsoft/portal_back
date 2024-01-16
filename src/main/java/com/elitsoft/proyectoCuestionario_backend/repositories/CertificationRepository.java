package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
