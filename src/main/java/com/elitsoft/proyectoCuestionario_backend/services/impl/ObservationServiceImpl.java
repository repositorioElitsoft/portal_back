package com.elitsoft.proyectoCuestionario_backend.services.impl;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.*;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @Autowired
    private ObservationCategoryRepository observationCategoryRepository;

    @Override
    public Boolean guardarObservacionRec(Observation observation, Long userId, Long usr_id_obs, Long usr_id_obs_mod) {
        try {

            if (!usr_id_obs.equals(usr_id_obs_mod)) {
                throw new IllegalArgumentException("El usuario que crea y modifica la observación debe ser el mismo.");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

            observation.setUser(user);
            observation.setUsr_id_obs(userId);
            observation.setUsr_id_obs(usr_id_obs);
            observation.setUsr_id_obs_mod(usr_id_obs);

            observation.setCreationDate(new Date());
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
    public Boolean guardarObservacionCat(Observation observation, Long userId, Long catObsId, Long usr_id_obs, Long usr_id_obs_mod) {

        try {
            if (!usr_id_obs.equals(usr_id_obs)) {
                throw new IllegalArgumentException("El usuario que crea la observación debe ser el mismo que el proporcionado.");
            }

            ObservationCategory observationCategory = observationCategoryRepository.findById(catObsId).orElse(null);
            if (observationCategory != null) {
                observation.setObservationCategory(observationCategory);
                observation.setCreationDate(new Date());

                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

                observation.setUser(user);

                observation.setUsr_id_obs(userId);

                observation.setUsr_id_obs(usr_id_obs);

                observation.setUsr_id_obs_mod(usr_id_obs);

                observationRepository.save(observation);

                return true;
            } else {
                throw new IllegalArgumentException("La categoría de observación con ID " + catObsId + " no existe.");
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Observation actualizarObservacionRec(Long obs_id, Observation observationActualizada, Long usr_id_obs_mod) {
        try {
            Optional<Observation> observacionOptional = observationRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observation observationExistente = observacionOptional.get();

                observationExistente.setUsr_id_obs_mod(usr_id_obs_mod);
                observationExistente.setModificationDate(new Date());
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
    public Observation actualizarObservacionCat(Long obs_id, Long catObsId, Observation observationActualizada2, Long usr_id_obs_mod) {
        try {
            //  Obtener la observación por su ID
            Optional<Observation> observacionOptional = observationRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observation observationExistente = observacionOptional.get();

                observationExistente.setUsr_id_obs_mod(usr_id_obs_mod);
                observationExistente.setModificationDate(new Date()); // update a fecha

                observationExistente.setUsr_id_obs_mod(usr_id_obs_mod);

                // Ejemplo: Obtener la nueva categoría de observación por su ID
                ObservationCategory observationCategory = observationCategoryRepository.findById(catObsId).orElse(null);
                if (observationCategory != null) {

                    observationExistente.setObservationCategory(observationCategory);
                    observationExistente.setDescription(observationActualizada2.getDescription());
                    observationExistente.setOperationalApproval(observationActualizada2.getOperationalApproval());
                    observationExistente.setTechnicalApproval(observationActualizada2.getTechnicalApproval());
                    observationExistente.setManagementApproval(observationActualizada2.getManagementApproval());

                    return observationRepository.save(observationExistente);
                } else {
                    throw new IllegalArgumentException("La categoría de observación con ID " + catObsId + " no existe.");
                }
            } else {
                throw new IllegalArgumentException("La observación con ID " + obs_id + " no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la observación con nueva categoría.");
        }
    }


    @Override
    public List<Observation> obtenerObservacionesPorUsuario(Long userId) {
        Optional<User> usuario = userRepository.findById(userId);
        return usuario.map(observationRepository::findByUser).orElseGet(Collections::emptyList);
    }


    @Override
    public List<ObservacionDTO> findObservacionUsuarioDetails(Long usr_id) {
        List<Observation> observations = observationRepository.findByUser(new User());

        return new ArrayList<>();
    }

    @Override
    public List<CatObservacionDTO> findCatObservacionUsuarioDetails(Long usr_id) {
        List<Observation> observations = observationRepository.findByUser(new User());

        return new ArrayList<>();
    }

    }
