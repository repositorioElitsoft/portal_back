
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria_Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface CategoriaProductoRepository extends JpaRepository <Categoria_Producto, Long> {
    
    //List<Categoria_Producto> findByProductos(Producto producto);
    // Método para obtener todos los productos asociados a una categoría específica
    @Query("SELECT p FROM Producto p WHERE p.cat_prod_id.cat_prod_id = :cat_prod_id")
    List<Producto> findProductosByCategoriaId(@Param("cat_prod_id") Long cat_prod_id);
    
    // Método para obtener todas las categorías de productos
    List<Categoria_Producto> findAll();
    
     
    
}
