package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CityRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CountryRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City obtenerCiudadId(Long id) {
        return cityRepository.getReferenceById(id);
    }

    @Override
    public List<City> obtenerCiudades(){
        return cityRepository.findAll();
    }

    @Override
    public List<City> obtenerCiudadesPorEstado(Long stateId) {
        State state = new State();
        state.setId(stateId);
        return cityRepository.findAllByState(state);
    }
}
