package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import com.elitsoft.proyectoCuestionario_backend.servicios.CityService;
import com.elitsoft.proyectoCuestionario_backend.servicios.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public List<City> obtenerCiudades(){
        return cityService.obtenerCiudades();
    }

    @GetMapping("/{id}")
    public City obtenerCiudadId(@PathVariable("id") Long Id){
        return cityService.obtenerCiudadId(Id);
    }

    @GetMapping("/state/{stateId}")
    public List<City> obtenerCiudadesPorEstado(@PathVariable("stateId") Long stateId){
        return cityService.obtenerCiudadesPorEstado(stateId);
    }

}
