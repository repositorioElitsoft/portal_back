package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioService {
    
    CargoUsuario guardarCargo(CargoUsuario cargo, Long usr_id) throws Exception;

    List<CargoUsuario> obtenerCargosPorUsuario(Usuario usuario);
    
    List<CargoUsuario> obtenerListaCargos();
    
}
