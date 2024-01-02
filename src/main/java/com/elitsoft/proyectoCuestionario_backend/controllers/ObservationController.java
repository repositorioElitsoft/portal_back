package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservationController {
    @Autowired
    private ObservationService observationService;




    @PostMapping("/guardarRec/{userId}/{usr_id_obs}/{usr_id_obs_mod}")
    public ResponseEntity<Boolean> guardarObservacionRec(@RequestBody Observation observation, @PathVariable Long userId,
                                                         @PathVariable Long usr_id_obs, @PathVariable Long usr_id_obs_mod) {
        try {
            Boolean resultado = observationService.guardarObservacionRec(observation, userId, usr_id_obs, usr_id_obs_mod);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return ResponseEntity.status(500).body(false);
        }
    }


    @PutMapping("/actualizarRec/{obs_id}")
    public ResponseEntity<Observation> actualizarObservacionRec(@PathVariable Long obs_id, @RequestBody Observation observationActualizada,
                                                                @PathVariable Long usr_id_obs_mod) {
        try {
            Observation resultado = observationService.actualizarObservacionRec(obs_id, observationActualizada, usr_id_obs_mod);
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



}


