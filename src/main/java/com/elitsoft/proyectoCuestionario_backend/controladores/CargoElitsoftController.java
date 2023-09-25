package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.servicios.CargoElitsoftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/cargoselitsoft")
public class CargoElitsoftController {
    
    @Autowired
    private final CargoElitsoftService cargoElitsoftService;

    public CargoElitsoftController(CargoElitsoftService cargoElitsoftService) {
        this.cargoElitsoftService = cargoElitsoftService;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<CargoElitsoft>> obtenerListaCargosElitsoft() {
        List<CargoElitsoft> cargosElitsoft = cargoElitsoftService.obtenerListaCargosElitsoft();
        return new ResponseEntity<>(cargosElitsoft, HttpStatus.OK);
    }
    
    
    
}
