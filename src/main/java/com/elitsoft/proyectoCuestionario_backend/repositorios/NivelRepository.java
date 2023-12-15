
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Nivel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface NivelRepository extends JpaRepository <Nivel, Long>{
    
    // Método para obtener todos los niveles disponibles
    List<Nivel> findAll();
    
    Optional<Nivel> findById(Long id); // Método para buscar por ID


    // para retornar un boolean
    Nivel save(Nivel nivel);

    void deleteById(Long nivel);
}
