package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservationService {

    // Método para crear una nueva observación
    Observation createObservation(Observation observation, String jwt);
    // Método para consultar una observación por su ID
    List<ObservationDTO> getObservationsByUserJob(Long userJobId);
    // Método para eliminar una observación por su ID
    boolean deleteObservation(Long id);

    Observation updateObservation(Long observationId,Observation newObservation, String jwt);

}
