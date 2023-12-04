package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.ObservacionService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservacionController {
    @Autowired
    private ObservacionService observacionService;


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/por-usuario/{userId}")
    public ResponseEntity<List<Observacion>> obtenerObservacionesPorUsuario(@PathVariable Long userId) {
        List<Observacion> observaciones = observacionService.obtenerObservacionesPorUsuario(userId);
        return ResponseEntity.ok(observaciones);
    }

    @PostMapping("/guardarRec/{userId}/{usr_id_obs}/{usr_id_obs_mod}")
    public ResponseEntity<Boolean> guardarObservacionRec(@RequestBody Observacion observacion, @PathVariable Long userId,
                                                         @PathVariable Long usr_id_obs, @PathVariable Long usr_id_obs_mod) {
        try {
            Boolean resultado = observacionService.guardarObservacionRec(observacion, userId, usr_id_obs, usr_id_obs_mod);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }

    @PostMapping("/guardarCat/{userId}/{catObsId}/{usr_id_obs}/{usr_id_obs_mod}")
    public ResponseEntity<Boolean> guardarObservacionCat(@RequestBody Observacion observacion, @PathVariable Long userId,
                                                         @PathVariable Long catObsId, @PathVariable Long usr_id_obs, @PathVariable Long usr_id_obs_mod) {
        try {
            Boolean resultado = observacionService.guardarObservacionCat(observacion, userId, catObsId, usr_id_obs, usr_id_obs_mod);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }

    @PutMapping("/actualizarRec/{obs_id}")
    public ResponseEntity<Observacion> actualizarObservacionRec(@PathVariable Long obs_id, @RequestBody Observacion observacionActualizada,
                                                                @PathVariable Long usr_id_obs_mod) {
        try {
            Observacion resultado = observacionService.actualizarObservacionRec(obs_id, observacionActualizada, usr_id_obs_mod);
            if (resultado != null) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/actualizarCat/{obs_id}/{catObsId}/{usr_id_obs_mod}")
    public ResponseEntity<Observacion> actualizarObservacionCat(@PathVariable Long obs_id, @PathVariable Long catObsId,
                                                                @RequestBody Observacion observacionActualizada2, @PathVariable Long usr_id_obs_mod) {
        try {
            Observacion resultado = observacionService.actualizarObservacionCat(obs_id, catObsId, observacionActualizada2, usr_id_obs_mod);
            if (resultado != null) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


}

