package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StateService {

    public State obtenerEstadoId(Long id);

    List<State> obtenerEstados();

}