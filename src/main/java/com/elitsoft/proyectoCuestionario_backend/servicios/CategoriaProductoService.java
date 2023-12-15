
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface CategoriaProductoService {
    
    List<ProductCategory> listarCategorias(); //para obtener todas
    List<Producto> findProductosByCategoriaId(Long categoriaId);
    
    
}
