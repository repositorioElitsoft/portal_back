package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.CategoriaObservacion;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaObservacionRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaObservacionServiceImpl implements CategoriaObservacionService {


    @Autowired
    private CategoriaObservacionRepository categoriaObservacionRepository;


    @Override
    public CategoriaObservacion obtenerCategoriaPorId(Long catObsId) {
        return categoriaObservacionRepository.findById(catObsId).orElse(null);
    }

    @Override
    public List<CategoriaObservacion> obtenerCategoriaObservaciones() {
        return categoriaObservacionRepository.findAll();
    }
}
