package com.sistema.PortalElitsoft.Servicios;

import com.sistema.PortalElitsoft.Entidades.Usuario;
import com.sistema.PortalElitsoft.Entidades.UsuarioRol;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Elitsoft83
 */
public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String email) throws Exception ;
    
    public void eliminarUsuario(Long usr_id);
    
    Usuario iniciarSesion(String email, String usr_pass) throws Exception;

   // public Usuario obtenerUsuariobyID(Long usr_id) throws Exception;
    
  //  Set<Usuario> obtenerUsuarios() throws Exception ;
    
    List<Usuario> findAll();
    
    public String obtenerRolUsuarioPorEmail(String email) throws Exception ;
    
}
