package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface LaboralService {
    
    Laboral guardarLaboral(Laboral laboral, Long usr_id, List<Long> herramientaIds) throws Exception;
    ///List<Laboral> obtenerListaLaboralPorUsuario(Usuario usuario);
    
    //<Laboral> obtenerListaLaboral();

   // List<Laboral> obtenerLaboralesConHerramientasYProductosPorUsuario(Long usr_id);

    
    
}
