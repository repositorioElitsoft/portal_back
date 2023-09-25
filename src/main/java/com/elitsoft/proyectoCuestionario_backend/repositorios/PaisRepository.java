
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    
    
    public Pais getReferenceById(Long pais_id);
    //Pais findByPais_nom(String pais_nom);
    
     @Query("SELECT p FROM Pais p WHERE LOWER(p.pais_nom) = LOWER(:pais_nom)")
    Pais findByPais_nom(@Param("pais_nom") String pais_nom);
    
}
