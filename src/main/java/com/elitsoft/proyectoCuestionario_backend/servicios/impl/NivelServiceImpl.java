
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Nivel;
import com.elitsoft.proyectoCuestionario_backend.repositorios.NivelRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.NivelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class NivelServiceImpl implements NivelService {

    private final NivelRepository nivelRepository;

    @Autowired
    public NivelServiceImpl(NivelRepository nivelRepository) {
        this.nivelRepository = nivelRepository;
    }

    @Override
    public List<Nivel> findAll() {
        return nivelRepository.findAll();
    }
    
    @Override
    public Nivel findNivelById(Long id) {
        return nivelRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardar_nivel(Nivel nivel){
        nivelRepository.save(nivel);
        return true;
    }

    @Override
    public Boolean remove_nivel(Long nivel) {
        nivelRepository.deleteById(nivel);
        return true;

    }
    
}
