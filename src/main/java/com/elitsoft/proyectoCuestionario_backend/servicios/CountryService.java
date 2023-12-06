
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author ELITSOFT86
 */
@Service
public interface CountryService {
    
    public Country obtenerPaisId(Long id);
    List<Country> obtenerPaises();
    //List<State> obtenerEstadosPorPais(Long countryId);

    List<State> getStatesByCountry(Long countryId);
    
}
