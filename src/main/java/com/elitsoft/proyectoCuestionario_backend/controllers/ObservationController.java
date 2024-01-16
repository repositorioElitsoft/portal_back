package com.elitsoft.proyectoCuestionario_backend.controllers;


import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservationDTO;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/observations")
@CrossOrigin(origins = "http://localhost:4200")
public class ObservationController {
    @Autowired
    private ObservationService observationService;

    @PostMapping("/")
    public ResponseEntity<?> createObs(@RequestBody Observation observation, @RequestHeader ("Authorization")
            String jwt) {
        try {
            Observation nuevaObservacion = observationService.createObservation(observation, jwt );
            return new ResponseEntity<>(nuevaObservacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user-job/{userJobId}")
    public ResponseEntity<List<ObservationDTO>> getObservationsByUserJob(@PathVariable Long userJobId) {
        List<ObservationDTO> observations = observationService.getObservationsByUserJob(userJobId);
        return new ResponseEntity<>(observations, HttpStatus.OK);
    }
    @PutMapping("/{observationId}")
    public ResponseEntity<?> updateObservation(@PathVariable Long observationId,
                                                         @RequestBody Observation observation,
                                                         @RequestHeader("Authorization") String jwt){
        try {
            Observation updatedObservation = observationService.updateObservation(observationId, observation,jwt);
            return new ResponseEntity<Observation>(updatedObservation, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Exception>(e, HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarObservacion(@PathVariable Long id) {
        try {
            boolean eliminado = observationService.deleteObservation(id);
            if (eliminado) {
                return new ResponseEntity<>("{\"message\": \"Observación eliminada con éxito\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\": \"No se encontró la observación con el ID: " + id + "\"}", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}




