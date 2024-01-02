
package com.elitsoft.proyectoCuestionario_backend.services;

import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface ExamService {
    
    Exam agregarExamen(Exam exam);
    
    Exam actualizarExamen (Long examenId, Exam exam);
    
    List<Exam> obtenerExamenes();
    
    Exam obtenerExamen(Long exam_id);
    
    void eliminarExamen(Long examenId);
    
    List<Exam> listarExamenesDeUnaCategoria(ExamCategory examCategory);


    List<Exam> obtenerExamenesByUser(String jwt);
}
