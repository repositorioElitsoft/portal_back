
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Nivel;
import com.elitsoft.proyectoCuestionario_backend.servicios.NivelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/")
    public Boolean guardar_nivel(@RequestBody Nivel nivel)  {
        nivelService.guardar_nivel(nivel);
        return  true;
    }

    @DeleteMapping("/{nivel}")
    public Boolean remove_nivel(@PathVariable Long nivel){
        nivelService.remove_nivel(nivel);
        return true;
    }

}
