
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface ProductoService {
    
    List<Producto> findByCategoriaId(Long cat_prod_id);
    
    List<Producto> findAll();
    
    // Método para obtener el nombre del producto por su ID
    String obtenerNombreProducto(Long prdId);
    
    
}
