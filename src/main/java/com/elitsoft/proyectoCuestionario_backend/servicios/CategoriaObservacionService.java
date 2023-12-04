package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CategoriaObservacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaObservacionService {

    CategoriaObservacion obtenerCategoriaPorId(Long catObsId);

    List<CategoriaObservacion> obtenerCategoriaObservaciones();
}
