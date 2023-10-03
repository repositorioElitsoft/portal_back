
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoElitsoftService {
    
    List<CargoElitsoft> obtenerListaCargosElitsoft();


    public Boolean guardar_cargos(CargoElitsoft cargo);

    public Boolean remove_cargo(Long cargo);
}
