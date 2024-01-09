package com.elitsoft.proyectoCuestionario_backend.services.impl;
import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.repositories.*;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.util.*;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJobRepository userJobRepository;
    @Autowired
    private UserService userService;


    @Override
    public Boolean guardarObservacionRec(Observation observation, Long userId, Long usr_id_obs, Long usr_id_obs_mod) {
        try {

            if (!usr_id_obs.equals(usr_id_obs_mod)) {
                throw new IllegalArgumentException("El usuario que crea y modifica la observación debe ser el mismo.");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

              observationRepository.save(observation);

            return true;
        } catch (DataAccessException e) {

            e.printStackTrace();
            throw new RuntimeException("Error al guardar la observación en la base de datos.", e);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Error desconocido al guardar la observación.", e);
        }
    }



    @Override
    public Observation actualizarObservacionRec(Long obs_id, Observation observationActualizada, Long usr_id_obs_mod) {
        try {
            Optional<Observation> observacionOptional = observationRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observation observationExistente = observacionOptional.get();


                observationExistente.setDescription(observationActualizada.getDescription());

                return observationRepository.save(observationExistente);
            } else {
                throw new IllegalArgumentException("La observación con ID " + obs_id + " no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Considera manejar esta excepción de manera más específica.
            return null;
        }
    }


    @Override
    public Observation crearObservacion(Observation observation, Long jobUserId, String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return null;
        }
        Optional<User> usuarioRec = userRepository.findByEmail(token.getPrincipal().toString());
        if (!usuarioRec.isPresent()){
            return null;
        }
        Optional<UserJob> userJobOptional = userJobRepository.findById(jobUserId);
        if (!userJobOptional.isPresent()){
            return null;
        }
        observation.setResponsibleId(usuarioRec.get().getId());
        observation.setUserJob(userJobOptional.get());
        try {
            return observationRepository.save(observation);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la observación en la base de datos.", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error desconocido al crear la observación.", e);
        }

    }

    @Override
    public List<Observation> listarObservaciones() {
        try {
            return observationRepository.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la lista de observaciones.", e);
        }
    }

    @Override
    public Observation consultarObservacion(Long id) {
        try {
            return observationRepository.findById(id).orElse(null);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consultar la observación con ID: " + id, e);
        }
    }

    @Override
    public boolean eliminarObservacion(Long id) {
        try {
            if (observationRepository.existsById(id)) {
                observationRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la observación con ID: " + id, e);
        }
    }
}








