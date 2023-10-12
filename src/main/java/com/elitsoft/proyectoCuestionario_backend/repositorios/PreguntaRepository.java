
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;

import java.util.List;
import java.util.Set;

import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface PreguntaRepository extends JpaRepository <Pregunta, Long>{
   // Set<Pregunta> findByExamen(Examen examen);

    @Query("SELECT p FROM Pregunta p WHERE p.prg_id.id = :exam_id")
    List<Pregunta> findByExamenId(@Param("exam_id") Long exam_id);
    
}
