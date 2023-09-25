
package com.sistema.PortalElitsoft.Servicios;

import com.sistema.PortalElitsoft.Entidades.Categoria;
import java.util.Set;

/**
 *
 * @author Noelid Chávez
 */
public interface CategoriaService {
    
    Categoria agregarCategoria(Categoria categoria);
    
    Categoria actualizarCategoria(Categoria categoria);

    Set<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long cat_exam_id);

    void eliminarCategoria(Long cat_exam_id);
    
   
    
}
