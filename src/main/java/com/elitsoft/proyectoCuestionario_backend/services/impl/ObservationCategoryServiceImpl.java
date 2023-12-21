package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.ObservationCategory;
import com.elitsoft.proyectoCuestionario_backend.repositories.ObservationCategoryRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationCategoryServiceImpl implements ObservationCategoryService {


    @Autowired
    private ObservationCategoryRepository observationCategoryRepository;


    @Override
    public ObservationCategory obtenerCategoriaPorId(Long catObsId) {
        return observationCategoryRepository.findById(catObsId).orElse(null);
    }

    @Override
    public List<ObservationCategory> obtenerCategoriaObservaciones() {
        return observationCategoryRepository.findAll();
    }
}
