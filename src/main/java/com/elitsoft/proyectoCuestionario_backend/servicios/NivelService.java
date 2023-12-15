
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Nivel;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface NivelService {
    
    List<Nivel> findAll();
    Nivel findNivelById(Long id); // Nuevo método para buscar por ID

    public Boolean guardar_nivel(Nivel nivel);

    public Boolean remove_nivel(Long nivel);
}
