
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CountryRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CountryServiceImpl implements CountryService {
    
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country obtenerPaisId(Long id) {
        return countryRepository.getReferenceById(id);
    }

    @Override
    public List<Country> obtenerPaises(){
        return countryRepository.findAll();
    }

    @Override
    public List<State> obtenerEstadosPorPais(Long countryId) {
        return countryRepository.findStatesByCountryId(countryId);
    }

    
}
