package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.VersionProducto;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ProductoRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.VersionProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.servicios.VersionProductoService;

import java.util.List;

@Service
public class VersionProductoServiceImpl implements VersionProductoService{

    private final ProductoRepository productoRepository;
    private final VersionProductoRepository versionProductoRepository;

    @Autowired
    public VersionProductoServiceImpl(ProductoRepository productoRepository, VersionProductoRepository versionProductoRepository) {
        this.productoRepository = productoRepository;
        this.versionProductoRepository = versionProductoRepository;
    }

    @Override
    public List<VersionProducto> findByProductoId(Long prod_id) throws Exception{
        Producto producto = productoRepository.findById(prod_id)
                .orElseThrow(() -> new IllegalArgumentException("Proudcto no encontrada con el ID: " + prod_id));

        return versionProductoRepository.findByProd_Id(prod_id);
    }
}
