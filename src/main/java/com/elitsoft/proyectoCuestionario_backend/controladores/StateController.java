package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.servicios.CityService;
import com.elitsoft.proyectoCuestionario_backend.servicios.CountryService;
import com.elitsoft.proyectoCuestionario_backend.servicios.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@CrossOrigin("*")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public List<State> obtenerEstados() {
        return stateService.obtenerEstados();
    }

    @GetMapping("/{id}")
    public State obtenerEstadoId(@PathVariable("id") Long Id) {
        return stateService.obtenerEstadoId(Id);
    }

}
