
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.stereotype.Service;


/**
 *
 * @author Maeva Martínez
 */
@Service
public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Usuario obtenerUsuario(Long usr_id)throws Exception ;

    public Boolean verificarUsuario(String code);
    
}
