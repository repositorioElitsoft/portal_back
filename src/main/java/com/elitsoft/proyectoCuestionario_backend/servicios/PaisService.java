
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import org.springframework.stereotype.Service;

/**
 *
 * @author ELITSOFT86
 */
@Service
public interface PaisService {
    
    public Pais obtenerPais(Long pais_id);
    Pais obtenerPaisPorNombre(String pais_nom);
    
    
}
