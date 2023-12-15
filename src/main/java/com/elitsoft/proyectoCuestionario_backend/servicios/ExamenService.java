
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.ExamCategory;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface ExamenService {
    
    Examen agregarExamen(Examen examen);
    
    Examen actualizarExamen (Long examenId, Examen examen );
    
    List<Examen> obtenerExamenes();
    
    Examen obtenerExamen(Long exam_id);
    
    void eliminarExamen(Long examenId);
    
    List<Examen> listarExamenesDeUnaCategoria(ExamCategory examCategory);


    List<Examen> obtenerExamenesByUser(String jwt);
}
