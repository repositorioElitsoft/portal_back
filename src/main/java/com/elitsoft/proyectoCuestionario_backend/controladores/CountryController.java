
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.servicios.CountryService;
import com.elitsoft.proyectoCuestionario_backend.servicios.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Maeva Martínez 
 */
@RestController
@RequestMapping("/country")
@CrossOrigin("*")
public class CountryController {
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @GetMapping("/")
    public List<Country> obtenerPaises(){
        return countryService.obtenerPaises();
    }
    
    @GetMapping("/{id}")
    public Country obtenerPaisId(@PathVariable("id") Long id){
        return countryService.obtenerPaisId(id);
    }

    @GetMapping("/{countryId}/states")
    public List<State> obtenerEstadosPorPais(@PathVariable("countryId") Long countryId) {
        return countryService.obtenerEstadosPorPais(countryId);
    }

    
}
