
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.ExamCategory;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface ExamCategoryService {
    
    
    ExamCategory agregarCategoria(ExamCategory examCategory);

    ExamCategory actualizarCategoria(Long categoriaId, ExamCategory examCategory);

    List<ExamCategory> obtenerCategorias();

    ExamCategory obtenerCategoria(Long categoriaId);

    void eliminarCategoria(Long categoriaId);
    
}
