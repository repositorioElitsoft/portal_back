
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface CategoriaService {
    
    
    Categoria agregarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    List<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long cat_exam_id);

    void eliminarCategoria(Long cat_exam_id);
    
}
