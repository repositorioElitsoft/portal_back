package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.ObservacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservacionService {


    Boolean guardarObservacionRec(Observacion observacion, Long userId, Long usr_id_obs, Long usr_id_obs_mod); // felipe


    Observacion actualizarObservacionRec(Long obs_id, Observacion observacionActualizada, Long usr_id_obs_mod); //felipe


    List<Observacion> obtenerObservacionesPorUsuario(Long userId); // felipe y mio

    List<ObservacionDTO> findObservacionUsuarioDetails(Long usr_id);

    List<CatObservacionDTO> findCatObservacionUsuarioDetails(Long usr_id);

    Boolean guardarObservacionCat(Observacion observacion, Long userId, Long catObsId, Long usr_id_obs, Long usr_id_obs_mod); // mio


    Observacion actualizarObservacionCat(Long obs_id, Long catObsId, Observacion observacionActualizada2, Long usr_id_obs_mod); //mio


}
