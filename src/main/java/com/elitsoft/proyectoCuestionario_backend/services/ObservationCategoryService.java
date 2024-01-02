package com.elitsoft.proyectoCuestionario_backend.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservationCategoryService {

    ObservationCategory obtenerCategoriaPorId(Long catObsId);

    List<ObservationCategory> obtenerCategoriaObservaciones();
}
