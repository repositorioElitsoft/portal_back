
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/categoria-productos")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaProductoController {
    
    @Autowired
    private final CategoriaProductoService categoriaProductoService;

    
    public CategoriaProductoController(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @GetMapping("/")
    public List<ProductCategory> listarCategorias() {
        return categoriaProductoService.listarCategorias();
    }

    @GetMapping("/{categoriaId}/productos")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable Long cat_prod_id) {
        return categoriaProductoService.findProductosByCategoriaId(cat_prod_id);
    }
    
    
}
