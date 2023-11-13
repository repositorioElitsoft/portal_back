package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.ObservacionService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservacionController {
    @Autowired
    private ObservacionService observacionService;


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Observacion>> obtenerObservacionesPorUsuario(@PathVariable Long usuarioId) throws Exception {
        Usuario usuario = usuarioService.obtenerUsuarioId(usuarioId);
        List<Observacion> observaciones = observacionService.obtenerObservacionesPorUsuario(usuario);
        return ResponseEntity.ok(observaciones);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Observacion> guardarObservacion(@RequestBody Observacion observacion) {
        Observacion nuevaObservacion = observacionService.guardarObservacion(observacion);
        return ResponseEntity.ok(nuevaObservacion);
    }

    @DeleteMapping("/eliminar/{observacionId}")
    public ResponseEntity<Void> eliminarObservacion(@PathVariable Long observacionId) {
        observacionService.eliminarObservacion(observacionId);
        System.out.println("Observacion eliminada");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{obs_id}")
    public ResponseEntity<Observacion> actualizarObservacion(@PathVariable Long obs_id, @RequestBody Observacion observacion) {
        try {
            Observacion observacionActualizada = observacionService.actualizarObservacion(obs_id, observacion);
            return ResponseEntity.ok(observacionActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

