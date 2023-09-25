
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PaisRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class PaisServiceImpl implements PaisService {
    
    @Autowired
    private PaisRepository paisRepository;

    @Override
    public Pais obtenerPais(Long pais_id) {
        return paisRepository.getReferenceById(pais_id);
    }
    
    @Override
    public Pais obtenerPaisPorNombre(String pais_nom) {
        return paisRepository.findByPais_nom(pais_nom);
    }
    
   
    
    
    
}
