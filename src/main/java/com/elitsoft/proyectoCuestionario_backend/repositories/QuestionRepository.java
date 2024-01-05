
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.Level;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
public interface QuestionRepository extends JpaRepository <Question, Long>{
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_qst WHERE qst_id = :id", nativeQuery = true)
    void eliminarPregunta(@Param("id") Long id);

    /**
     * Busca preguntas por dificultad de Level y id de Product.
     *
     * @param difficulty Dificultad de Level.
     * @param productId   Id de Product.
     * @return Lista de preguntas que coinciden con los criterios de búsqueda.
     */
    List<Question> findByLevelAndProduct(Level level, Product product);

    // Otros métodos si es necesario


}