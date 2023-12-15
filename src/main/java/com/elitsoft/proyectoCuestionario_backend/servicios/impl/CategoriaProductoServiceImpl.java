
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaProductoRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService{
    
    @Autowired
    private final CategoriaProductoRepository categoriaProductoRepository;

    
    public CategoriaProductoServiceImpl(CategoriaProductoRepository categoriaProductoRepository) {
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    @Override
    public List<ProductCategory> listarCategorias() {
        return categoriaProductoRepository.findAll();
    }

    @Override
    public List<Producto> findProductosByCategoriaId(Long categoriaId) {
         return categoriaProductoRepository.findProductosByCategoriaId(categoriaId);
    }
    
}
