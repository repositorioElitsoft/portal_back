package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entidades.User;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioService {

    Boolean guardarCargo(UserJob cargo, String jwt, Date fechaPostulacion) throws Exception;
    List<UserJob> obtenerCargosPorUsuario(User user);
    
    List<UserJob> obtenerListaCargos();

    UserJob obtenerCargoUsuario(String jwt) throws Exception;

    void eliminarCargoPorUsuario(Long usuarioId);

    Boolean actualizarDisponibilidadLaboral(String disponibilidadLaboral, String jwt) throws  Exception;
    
}
