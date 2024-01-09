package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservationService {

    // Método existente para guardar una observación
    Boolean guardarObservacionRec(Observation observation, Long userId, Long usr_id_obs, Long usr_id_obs_mod); // felipe

    // Método existente para actualizar una observación
    Observation actualizarObservacionRec(Long obs_id, Observation observationActualizada, Long usr_id_obs_mod); //felipe

    // Método para crear una nueva observación
    Observation crearObservacion(Observation observation, Long jobUserId, String jwt);

    // Método para listar todas las observaciones
    List<Observation> listarObservaciones();

    // Método para consultar una observación por su ID
    Observation consultarObservacion(Long id);

    // Método para eliminar una observación por su ID
    boolean eliminarObservacion(Long id);
}
