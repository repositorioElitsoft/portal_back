package com.sistema.PortalElitsoft.Servicios.impl;

import com.sistema.PortalElitsoft.Entidades.Usuario;
import com.sistema.PortalElitsoft.Entidades.UsuarioRol;
import com.sistema.PortalElitsoft.Repository.RolRepository;
import com.sistema.PortalElitsoft.Repository.UsuarioRepository;
import com.sistema.PortalElitsoft.Servicios.UsuarioService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Elitsoft83
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
       
      // Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
      // Optional<Usuario> usuarioLocal = usuarioRepository.findById(usuario.getUsr_id());
        Usuario usuarioLocal = usuarioRepository.findByEmail(usuario.getEmail());
       if(usuarioLocal != null){
       // if (usuarioLocal.isPresent()) {
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
           // usuarioLocal = Optional.of(usuarioRepository.save(usuario));
            usuarioLocal = usuarioRepository.save(usuario);
        }
       // return usuarioLocal.get();
        return usuarioLocal;
    }
    
    @Override
    public Usuario obtenerUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
 //   @Override
 //   public Usuario obtenerUsuariobyID(Long usr_id) {
 //       return usuarioRepository.getReferenceById(usr_id);
 //  }

    @Override
    public void eliminarUsuario(Long usr_id) {
        usuarioRepository.deleteById(usr_id);
    }
    
    @Override
    public Usuario iniciarSesion(String email, String usr_pass) throws Exception {
        // Buscar el usuario por su correo electrónico
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        // Verificar si el usuario existe
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        
        // Verificar si la contraseña coincide
        if (!usuario.getUsr_pass().equals(usr_pass)) {
            throw new Exception("Contraseña incorrecta");
        }
        
        // Si las credenciales son válidas, devolver el usuario
        return usuario;
    }
    


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    } 
    
    
    @Override
    public String obtenerRolUsuarioPorEmail(String email) throws Exception {
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null && !usuario.getUsuarioRoles().isEmpty()) {
        return usuario.getUsuarioRoles().iterator().next().getRol().getRol_nom();
    }
    
    return null;
    }
}
