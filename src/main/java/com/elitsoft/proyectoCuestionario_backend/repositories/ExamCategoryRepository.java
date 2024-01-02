
package com.elitsoft.proyectoCuestionario_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author Maeva Martínez
 */
public interface ExamCategoryRepository extends JpaRepository<ExamCategory, Long> {

    Optional<ExamCategory> findById(Long categoriaId);
    
}
