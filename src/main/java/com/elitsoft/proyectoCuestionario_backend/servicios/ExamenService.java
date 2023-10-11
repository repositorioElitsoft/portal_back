
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
    
    List<Examen> obtenerExamenes();
    
    Examen obtenerExamen(Long exam_id);
    
    void eliminarExamen(Long exam_id);
    
    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);

    
}
