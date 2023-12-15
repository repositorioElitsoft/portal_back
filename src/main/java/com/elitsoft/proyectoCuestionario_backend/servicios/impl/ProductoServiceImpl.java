
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria_Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaProductoRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ProductoRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ProductoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaProductoRepository categoriaProductoRepository) {
        this.productoRepository = productoRepository;
        this.categoriaProductoRepository = categoriaProductoRepository;
    }
    
   
    @Override
    public List<Producto> findByCategoriaId(Long cat_prod_id) {
        Categoria_Producto categoriaProducto = categoriaProductoRepository.findById(cat_prod_id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con el ID: " + cat_prod_id));
        return productoRepository.findByCat_prod_id_Id(cat_prod_id);
    }


    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    @Override
    public String obtenerNombreProducto(Long prdId) {
        return productoRepository.findNombreProductoById(prdId);
    }


    @Override
    public Boolean guardar_producto(Producto producto){
        productoRepository.save(producto);
        return true;
    }

    @Override
    public Boolean remove_producto(Long producto) {
        productoRepository.deleteById(producto);
        return true;

    }


}
