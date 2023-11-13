package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CountryRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.StateRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public State obtenerEstadoId(Long id) {
        return stateRepository.getReferenceById(id);
    }

    @Override
    public List<State> obtenerEstados(){
        return stateRepository.findAll();
    }



}
