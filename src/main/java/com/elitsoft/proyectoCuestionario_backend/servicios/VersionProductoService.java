package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.VersionProducto;

import java.util.List;

public interface VersionProductoService {

    List<VersionProducto> findByProductoId(Long prod_id) throws Exception;
}
