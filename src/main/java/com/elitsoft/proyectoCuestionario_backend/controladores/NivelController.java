
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Nivel;
import com.elitsoft.proyectoCuestionario_backend.servicios.NivelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez 
 */
@RestController
@RequestMapping("/niveles")
@CrossOrigin(origins = "http://localhost:4200")
public class NivelController {

    @Autowired
    private final NivelService nivelService;

    
    public NivelController(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Nivel>> listarNiveles() {
        List<Nivel> niveles = nivelService.findAll();
        return new ResponseEntity<>(niveles, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Nivel> obtenerNivelPorId(@PathVariable Long id) {
        Nivel nivel = nivelService.findNivelById(id);
        if (nivel != null) {
            return new ResponseEntity<>(nivel, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
