
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
public interface PreguntaRepository extends JpaRepository <Pregunta, Long>{
    
    List<Pregunta> findByExamen(Examen examen);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_prg WHERE prg_id = :id", nativeQuery = true)
    void eliminarPregunta(@Param("id") Long id);

}
