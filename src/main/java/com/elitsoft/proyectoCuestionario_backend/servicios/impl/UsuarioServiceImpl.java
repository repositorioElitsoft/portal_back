
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PaisRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private EmailServiceImpl emailService;
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

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.setUsr_pass(encoder.encode(usuario.getUsr_pass()));
        usuario.setUsr_ver_code(UUID.randomUUID().toString());

        usuario.setUsr_is_ver(false);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        emailService.sendVerificationEmail(nuevoUsuario);

        return nuevoUsuario;
    }

    @Override
    public Boolean verificarUsuario(String code){

        Optional<Usuario> user = usuarioRepository.findByUsrVerCode(code);

        if (!user.isPresent()){
            return false;
        }

        Usuario presentUser = user.get();
        presentUser.setUsr_is_ver(true);

        Usuario savedUser = usuarioRepository.save(presentUser);

        return savedUser.getUsr_is_ver();
    }
    
    @Override
    public Usuario obtenerUsuario(Long usr_id) {
        return usuarioRepository.findById(usr_id).orElse(null);
    }

    @Override
    public Boolean cambiarPassword(String code, String password){

        if(code.isEmpty()){
            return false;
        }

        Optional<Usuario> usuario = usuarioRepository.findByUsrRecPassCode(code);
        if (!usuario.isPresent()){
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.get().setUsr_rec_tkn("");
        usuario.get().setUsr_pass(encoder.encode(password));

        Usuario savedUser = usuarioRepository.save(usuario.get());
        return true;
    }
    @Override
    public void pedirRestaurarPassword(Usuario usuarioEntrante) throws MessagingException, UnsupportedEncodingException {

        String email = usuarioEntrante.getUsr_email();

        Optional<Usuario> usuario = usuarioRepository.findByUsrEmail(email);
        if (!usuario.isPresent()){
            return;
        }
        usuario.get().setUsr_rec_tkn(UUID.randomUUID().toString());

        Usuario usuarioActualizado = usuarioRepository.save(usuario.get());

        if (!usuarioActualizado.getUsr_email().equals(email)){
            return;
        }
        emailService.sendRecoverPassword(usuarioActualizado);
    }


    
}
