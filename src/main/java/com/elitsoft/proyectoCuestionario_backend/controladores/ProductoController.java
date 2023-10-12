package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.servicios.ProductoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProductoController {

    @Autowired
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint para obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    // Endpoint para obtener los productos por categoría
    @GetMapping("/producto-por-categoria/{cat_prod_id}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable Long cat_prod_id) {
        List<Producto> productos = productoService.findByCategoriaId(cat_prod_id);
        return ResponseEntity.ok(productos);
    }
    
    // Endpoint para obtener el nombre del producto por su ID
    @GetMapping("/nombre-producto/{productoId}")
    public ResponseEntity<String> obtenerNombreProducto(@PathVariable Long productoId) {
        String nombreProducto = productoService.obtenerNombreProducto(productoId);
        return ResponseEntity.ok(nombreProducto);
    }


    @PostMapping("/")
    public Boolean guardar_producto (@RequestBody Producto producto )  {
        productoService.guardar_producto(producto);
        return  true;
    }

    @DeleteMapping("/{producto}")
    public Boolean remove_producto(@PathVariable Long producto){
        productoService.remove_producto(producto);
        return true;
    }
}
