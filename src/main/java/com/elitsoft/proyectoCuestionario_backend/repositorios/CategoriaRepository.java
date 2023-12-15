
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.ExamCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author Maeva Martínez
 */
public interface CategoriaRepository extends JpaRepository<ExamCategory, Long> {

    Optional<ExamCategory> findById(Long categoriaId);
    
}
