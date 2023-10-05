
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 *
 * @author Maeva Martínez
 */
@Service
public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Usuario obtenerUsuario(Long usr_id)throws Exception ;

    public Boolean verificarUsuario(Map<String,String> body);

    public Optional<Usuario> getUsuarioByToken(String jwt);
    public void pedirRestaurarPassword(Usuario usuario) throws MessagingException, UnsupportedEncodingException;
    public Boolean cambiarPassword(String code, String password);

    public Boolean actualizarUsuario(Usuario usuario, String jwt);

    Usuario obtenerDatosUsuario(String jwt) throws Exception;
    
}
