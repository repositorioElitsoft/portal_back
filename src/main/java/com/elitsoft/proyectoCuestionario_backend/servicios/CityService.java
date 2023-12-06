package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    public City obtenerCiudadId(Long id);

    List<City> obtenerCiudades();

    //List<City> obtenerCiudadesPorEstado(Long stateId);
}
