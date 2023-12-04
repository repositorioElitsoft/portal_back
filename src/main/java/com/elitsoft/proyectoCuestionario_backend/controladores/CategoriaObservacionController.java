package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CategoriaObservacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catobs")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaObservacionController {

    @Autowired
    private CategoriaObservacionService categoriaObservacionService;

    @GetMapping("/{catObsId}")
    public ResponseEntity<CategoriaObservacion> obtenerCategoriaPorId(@PathVariable Long catObsId) {
        CategoriaObservacion categoria = categoriaObservacionService.obtenerCategoriaPorId(catObsId);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public List<CategoriaObservacion> obtenerCategoriaObservaciones(){
        return categoriaObservacionService.obtenerCategoriaObservaciones();
    }


}
