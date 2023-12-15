
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.ExamCategory;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface CategoriaService {
    
    
    ExamCategory agregarCategoria(ExamCategory examCategory);

    ExamCategory actualizarCategoria(Long categoriaId, ExamCategory examCategory);

    List<ExamCategory> obtenerCategorias();

    ExamCategory obtenerCategoria(Long categoriaId);

    void eliminarCategoria(Long categoriaId);
    
}
