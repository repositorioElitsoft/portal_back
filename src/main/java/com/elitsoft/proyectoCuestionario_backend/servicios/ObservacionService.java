package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservacionService {


    Observacion guardarObservacion(Observacion observacion);

    List<Observacion> obtenerObservacionesPorUsuario(Usuario usuario);

    void eliminarObservacion(Long observacionId);

    Observacion actualizarObservacion(Long obs_id, Observacion observacionActualizada);
}
