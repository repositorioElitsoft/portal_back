
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    // Método para obtener productos filtrados por categoría
    @Query("SELECT p FROM Product p WHERE p.productCategory.id = :cat_prod_id")
    List<Product> findByCat_prod_id_Id(@Param("cat_prod_id") Long cat_prod_id);

    @Query("SELECT p FROM Product p WHERE p.name = :prd_nom")
    Optional<Product> findByPrd_nom(@Param("prd_nom") String prd_nom);
    
    // Método para obtener todos los productos disponibles
    List<Product> findAll();
    
    // Método para obtener el nombre del producto por su ID
    @Query("SELECT p.name FROM Product p WHERE p.id = :prdId")
    String findNombreProductoById(@Param("prdId") Long prdId);


    // para retornar un boolean
    Product save(Product product);
    void deleteById(Long producto);

}
