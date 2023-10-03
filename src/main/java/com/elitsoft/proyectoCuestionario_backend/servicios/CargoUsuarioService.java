package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioService {
    
    Boolean guardarCargo(CargoUsuario cargo, String jwt) throws Exception;

    List<CargoUsuario> obtenerCargosPorUsuario(Usuario usuario);
    
    List<CargoUsuario> obtenerListaCargos();
    
}
