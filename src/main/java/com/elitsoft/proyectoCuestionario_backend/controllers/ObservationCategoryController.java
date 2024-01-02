package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.services.ObservationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catobs")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservationCategoryController {

    @Autowired
    private ObservationCategoryService observationCategoryService;

    @GetMapping("/{catObsId}")
    public ResponseEntity<ObservationCategory> obtenerCategoriaPorId(@PathVariable Long catObsId) {
        ObservationCategory categoria = observationCategoryService.obtenerCategoriaPorId(catObsId);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public List<ObservationCategory> obtenerCategoriaObservaciones(){
        return observationCategoryService.obtenerCategoriaObservaciones();
    }


}
