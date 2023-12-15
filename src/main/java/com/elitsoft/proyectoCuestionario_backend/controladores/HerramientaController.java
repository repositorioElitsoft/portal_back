
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;
import com.elitsoft.proyectoCuestionario_backend.servicios.HerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/herramientas")
@CrossOrigin(origins = "http://localhost:4200")
public class HerramientaController {

    private final HerramientaService herramientaService;
    private final LaboralService laboralService;
    
    @Autowired
    public HerramientaController(HerramientaService herramientaService, LaboralService laboralService) {
        this.herramientaService = herramientaService;
        this.laboralService = laboralService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarHerramientas(
            @RequestBody List<Herramienta> herramientas,
            @RequestHeader("Authorization") String Jwt) throws Exception {
        try {
            herramientaService.guardarHerramientas(herramientas, Jwt);
        }
        catch (ConstraintViolationException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerListaHerramientas(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Herramienta> herramientas = herramientaService.obtenerListaHerramientasPorUsuario(jwt);
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }


}

