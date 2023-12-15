
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.JobPosition;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoElitsoftService {
    
    List<JobPosition> obtenerListaCargosElitsoft();


    public Boolean guardar_cargos(JobPosition cargo);

    public Boolean remove_cargo(Long cargo);
}
