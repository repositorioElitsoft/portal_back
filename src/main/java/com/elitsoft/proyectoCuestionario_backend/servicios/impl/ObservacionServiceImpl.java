package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ObservacionRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ObservacionServiceImpl implements ObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    @Override
    public List<Observacion> obtenerObservacionesPorUsuario(Usuario usuario) {
        return observacionRepository.findByUsuario(usuario);
    }


    @Override
    public Observacion guardarObservacion(Observacion observacion) {
        return observacionRepository.save(observacion);
    }

    @Override
    public void eliminarObservacion(Long observacionId) {
        observacionRepository.deleteById(observacionId);
    }

    @Override
    public Observacion actualizarObservacion(Long obs_id, Observacion observacionActualizada) {
        Observacion observacionExistente = observacionRepository.findById(obs_id)
                .orElseThrow(() -> new EntityNotFoundException("Observación no encontrada"));

        observacionExistente.setObs_categoria(observacionActualizada.getObs_categoria());
        observacionExistente.setObs_texto(observacionActualizada.getObs_texto());
        observacionExistente.setObs_fecha_creacion(observacionActualizada.getObs_fecha_creacion());
        observacionExistente.setObs_final(observacionActualizada.getObs_final());

        System.out.println("observacion actualizada");

        return observacionRepository.save(observacionExistente);
    }



}
