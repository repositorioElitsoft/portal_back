
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
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

    public Usuario obtenerUsuarioId(Long usr_id)throws Exception ;

    public Boolean verificarUsuario(Map<String,String> body);
    public Usuario getUsuarioByEmail(String email);

    public Optional<Usuario> getUsuarioByToken(String jwt);
    public void pedirRestaurarPassword(Usuario usuario) throws MessagingException, UnsupportedEncodingException;
    public Boolean cambiarPassword(String code, String password);


    void uploadUserCv(String jwt, MultipartFile cv) throws IOException;
    public Boolean actualizarUsuario(Usuario usuario, String jwt);

    public Resource getCVByUser(String jwt) throws IOException;

    Usuario actualizarUsuarioId(Long usr_id, Usuario usuario);

    public List<Usuario> listarUsuarios();

    Usuario obtenerDatosUsuario(String jwt) throws Exception;

    public  List<Usuario> obtenerUsuario();

    public List<Usuario> listarUsuariosConHerramientas();

    void eliminarUsuarioId (Long usr_id);

    public Usuario guardarAdmin(Usuario usuario) throws Exception;

    public Usuario guardarRec(Usuario usuario) throws Exception;

}
