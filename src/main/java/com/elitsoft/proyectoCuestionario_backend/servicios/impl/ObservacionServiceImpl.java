package com.elitsoft.proyectoCuestionario_backend.servicios.impl;
import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ObservacionDTORepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ObservacionRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ObservacionService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ObservacionServiceImpl implements ObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private  ObservacionDTORepository observacionDTORepository;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Boolean guardarObservacionRec(Observacion observacion, Long userId, Long usr_id_obs, Long usr_id_obs_mod) {
        try {

            if (!usr_id_obs.equals(usr_id_obs_mod)) {
                throw new IllegalArgumentException("El usuario que crea y modifica la observación debe ser el mismo.");
            }

            Usuario usuario = usuarioRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

            observacion.setUsuario(usuario);
            observacion.setUsr_id_obs(userId);
            observacion.setUsr_id_obs(usr_id_obs);
            observacion.setUsr_id_obs_mod(usr_id_obs);

            observacion.setObs_fec_cre(new Date());
            observacionRepository.save(observacion);

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
    public Observacion actualizarObservacionRec(Long obs_id, Observacion observacionActualizada, Long usr_id_obs_mod) {
        try {
            Optional<Observacion> observacionOptional = observacionRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observacion observacionExistente = observacionOptional.get();

                observacionExistente.setUsr_id_obs_mod(usr_id_obs_mod);
                observacionExistente.setObs_fec_mod(new Date());
                observacionExistente.setObs_desc(observacionActualizada.getObs_desc());

                return observacionRepository.save(observacionExistente);
            } else {
                throw new IllegalArgumentException("La observación con ID " + obs_id + " no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Considera manejar esta excepción de manera más específica.
            return null;
        }
    }



    @Override
    public List<Observacion> obtenerObservacionesPorUsuario(Long userId) {
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        return usuario.map(observacionRepository::findByUsuario).orElseGet(Collections::emptyList);
    }







    @Override
    public List<ObservacionDTO> findObservacionUsuarioDetails(Long usr_id) {
        return observacionDTORepository.findObservacionUsuarioDetails(usr_id);
    }

    }
