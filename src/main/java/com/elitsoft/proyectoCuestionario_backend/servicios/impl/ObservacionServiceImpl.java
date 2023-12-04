package com.elitsoft.proyectoCuestionario_backend.servicios.impl;
import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ObservacionDTORepository;
import com.elitsoft.proyectoCuestionario_backend.entidades.CategoriaObservacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaObservacionRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ObservacionRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ObservacionService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private CategoriaObservacionRepository categoriaObservacionRepository;

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
    public Boolean guardarObservacionCat(Observacion observacion, Long userId, Long catObsId, Long usr_id_obs, Long usr_id_obs_mod) {

        try {
            if (!usr_id_obs.equals(usr_id_obs)) {
                throw new IllegalArgumentException("El usuario que crea la observación debe ser el mismo que el proporcionado.");
            }

            CategoriaObservacion categoriaObservacion = categoriaObservacionRepository.findById(catObsId).orElse(null);
            if (categoriaObservacion != null) {
                observacion.setCategoriaObservacion(categoriaObservacion);
                observacion.setObs_fec_cre(new Date());

                Usuario usuario = usuarioRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

                observacion.setUsuario(usuario);

                observacion.setUsr_id_obs(userId);

                observacion.setUsr_id_obs(usr_id_obs);

                observacion.setUsr_id_obs_mod(usr_id_obs);

                observacionRepository.save(observacion);

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
    public Observacion actualizarObservacionCat(Long obs_id, Long catObsId, Observacion observacionActualizada2, Long usr_id_obs_mod) {
        try {
            //  Obtener la observación por su ID
            Optional<Observacion> observacionOptional = observacionRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observacion observacionExistente = observacionOptional.get();

                observacionExistente.setUsr_id_obs_mod(usr_id_obs_mod);
                observacionExistente.setObs_fec_mod(new Date()); // update a fecha

                observacionExistente.setUsr_id_obs_mod(usr_id_obs_mod);

                // Ejemplo: Obtener la nueva categoría de observación por su ID
                CategoriaObservacion categoriaObservacion = categoriaObservacionRepository.findById(catObsId).orElse(null);
                if (categoriaObservacion != null) {

                    observacionExistente.setCategoriaObservacion(categoriaObservacion);
                    observacionExistente.setObs_desc(observacionActualizada2.getObs_desc());
                    observacionExistente.setApr_oper(observacionActualizada2.getApr_oper());
                    observacionExistente.setApr_tec(observacionActualizada2.getApr_tec());
                    observacionExistente.setApr_ger(observacionActualizada2.getApr_ger());

                    return observacionRepository.save(observacionExistente);
                } else {
                    throw new IllegalArgumentException("La categoría de observación con ID " + catObsId + " no existe.");
                }
            } else {
                throw new IllegalArgumentException("La observación con ID " + obs_id + " no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; //
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
