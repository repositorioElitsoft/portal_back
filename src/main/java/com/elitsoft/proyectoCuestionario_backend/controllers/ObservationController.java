package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservationController {
    @Autowired
    private ObservationService observationService;
    @Autowired
    private UserRepository userRepository;



    @PutMapping("/actualizar/{observacionId}/{usr_id_obs_mod}")
    public ResponseEntity<?> actualizarObservacion(@PathVariable Long observacionId, @RequestBody Observation observation, @PathVariable Long usr_id_obs_mod) {
        try {
            Observation observationActualizada = observationService.actualizarObservacionRec(observacionId, observation, usr_id_obs_mod);
            if (observationActualizada != null) {
                return ResponseEntity.ok(observationActualizada);
            } else {
                // Aquí puedes manejar el caso en el que la actualización no arroje un error pero tampoco retorne un resultado
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la observación con el ID especificado para actualizar.");
            }
        } catch (Exception e) {
            // Aquí manejas la excepción, e.g., imprimir la pila de llamadas o registrar el error
            // Puedes personalizar el mensaje de error según el tipo de excepción y la información que desees exponer
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la observación: " + e.getMessage());
        }
    }

    @PostMapping("/{jobUserId}")
    public ResponseEntity<?> createObs(@RequestBody Observation observation, @PathVariable Long jobUserId,@RequestHeader ("Authorization")
            String jwt) {

        try {
            Observation nuevaObservacion = observationService.crearObservacion(observation, jobUserId, jwt );
            return new ResponseEntity<>(nuevaObservacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Observation>> listarObservaciones() {
        List<Observation> observaciones = observationService.listarObservaciones();
        if (observaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(observaciones, HttpStatus.OK);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> getObs(@PathVariable Long id) {
        Observation observacion = observationService.consultarObservacion(id);
        if (observacion != null) {
            return new ResponseEntity<>(observacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Observación no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarObservacion(@PathVariable Long id) {
        try {
            boolean eliminado = observationService.eliminarObservacion(id);
            if (eliminado) {
                return new ResponseEntity<>("Observación eliminada con éxito", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontró la observación con el ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




