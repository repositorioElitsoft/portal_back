
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PaisRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PaisRepository paisRepository;
    
//    @Autowired
//    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        Long usrId = usuario.getUsr_id();

        if (usrId != null) {
            Optional<Usuario> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                System.out.println("El usuario ya existe");
                throw new Exception("El usuario ya está presente");
            }
        }

        Pais pais = usuario.getPais();

        // Asignamos el objeto Pais obtenido al atributo pais de la entidad Usuario
        usuario.setPais(pais);

        return usuarioRepository.save(usuario);
    }
    
    @Override
    public Usuario obtenerUsuario(Long usr_id) {
        return usuarioRepository.findById(usr_id).orElse(null);
    }

    
    
}
