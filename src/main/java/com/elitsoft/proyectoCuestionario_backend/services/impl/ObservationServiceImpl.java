package com.elitsoft.proyectoCuestionario_backend.services.impl;
import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.ObservationUpdate;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservationDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ResponsibleDTO;
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
import java.util.stream.Collectors;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private ObservationUpdateRepository observationUpdateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJobRepository userJobRepository;
    @Autowired
    private UserService userService;


    @Override
    public Observation createObservation(Observation observation, String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return null;
        }
        Optional<User> usuarioRec = userRepository.findByEmail(token.getPrincipal().toString());
        if (!usuarioRec.isPresent()){
            return null;
        }

        Observation newObservation = observationRepository.save(observation);
        ObservationUpdate observationUpdate = new ObservationUpdate();
        observationUpdate.setObservation(newObservation);
        observationUpdate.setDescription(newObservation.getDescription());
        observationUpdate.setResponsibleId(usuarioRec.get().getId());
        observationUpdateRepository.save(observationUpdate);
        return newObservation;
    }

    @Override
    public Observation updateObservation(Long observationId,Observation newObservation, String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return null;
        }
        Optional<User> usuarioRec = userRepository.findByEmail(token.getPrincipal().toString());
        if (!usuarioRec.isPresent()){
            return null;
        }
        Optional<Observation> oldObservation = observationRepository.findById(observationId);
        if(!oldObservation.isPresent()){
            return null;
        }

        newObservation.setId(oldObservation.get().getId());
        newObservation.setUserJob(oldObservation.get().getUserJob());

        Observation updatedObservation = observationRepository.save(newObservation);

        ObservationUpdate observationUpdate = new ObservationUpdate();
        observationUpdate.setObservation(updatedObservation);
        observationUpdate.setDescription(updatedObservation.getDescription());
        observationUpdate.setResponsibleId(usuarioRec.get().getId());
        observationUpdateRepository.save(observationUpdate);
        return updatedObservation;
    }


    @Override
    public List<ObservationDTO> getObservationsByUserJob(Long userJobId) {
        List<Observation> observations = observationRepository.findAllByUserJobId(userJobId).orElse(null);
        List<ObservationDTO> observationDTOS = Objects.requireNonNull(observations).stream().map(observation -> {
            ObservationDTO newObs = new ObservationDTO();
            newObs.setId(observation.getId());
            newObs.setDescription(observation.getDescription());

            ObservationUpdate oldest = observationUpdateRepository.findFirstRecord(observation.getId()).orElse(null);
            ObservationUpdate recent = observationUpdateRepository.findMostRecentRecord(observation.getId()).orElse(null);

            User authorUser = userRepository.findById(Objects.requireNonNull(oldest).getResponsibleId()).orElse(null);
            User modifierUser = userRepository.findById(Objects.requireNonNull(recent).getResponsibleId()).orElse(null);

            ResponsibleDTO author = new ResponsibleDTO();
            author.setEmail(authorUser.getEmail());
            author.setName(authorUser.getName());
            author.setFirstLastname(authorUser.getFirstLastname());
            author.setSecondLastname(authorUser.getSecondLastname());

            ResponsibleDTO modifier = new ResponsibleDTO();
            modifier.setEmail(modifierUser.getEmail());
            modifier.setName(modifierUser.getName());
            modifier.setFirstLastname(modifierUser.getFirstLastname());
            modifier.setSecondLastname(modifierUser.getSecondLastname());

            author.setDate(oldest.getUpdatedAt());
            modifier.setDate(recent.getUpdatedAt());

            newObs.setAuthor(author);
            newObs.setLastUpdateResponsible(modifier);

            return newObs;
        }).collect(Collectors.toList());

        try {
            return observationDTOS;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consultar la observación con ID: " + userJobId, e);
        }
    }

    @Override
    public boolean deleteObservation(Long id) {
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








