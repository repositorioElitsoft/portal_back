
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Maeva Martínez
 */
public interface ExamenService {
    
    Examen agregarExamen(Examen examen);
    
    Examen actualizarExamen (Examen examen);
    
    Set<Examen> obtenerExamenes();
    
    Examen obtenerExamen(Long examenId);
    
    void eliminarExamen(Long examenId);
    
    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);

    List<Examen> obtenerExamenesActivos();

    List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria);
   
    
  
    
}
