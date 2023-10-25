
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.Exceptions.MissingJwtException;
import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PaisRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.FileService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.*;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private FileService fileService;

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
        usuario.setUsr_rol("GUEST");

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        emailService.sendVerificationEmail(nuevoUsuario);

        return nuevoUsuario;
    }

    @Override
    public Boolean verificarUsuario(Map<String, String> body) {

        Optional<Usuario> user = usuarioRepository.findByUsrVerCode(body.get("code"));

        if (!user.isPresent()) {
            return false;
        }

        Usuario presentUser = user.get();
        presentUser.setUsr_is_ver(true);

        Usuario savedUser = usuarioRepository.save(presentUser);

        return savedUser.getUsr_is_ver();
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        Usuario user = usuarioRepository.findByUsrEmail(email).orElseGet(Usuario::new);
        user.setUsr_pass("");
        user.setUsr_rec_tkn("");
        user.setUsr_ver_code("");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return user;
    }


    @Override //aqui
    public Usuario obtenerUsuarioId(Long usr_id) {
        return usuarioRepository.findById(usr_id).orElse(null);
    }

    @Override
    public Boolean cambiarPassword(String code, String password) {

        if (code.isEmpty()) {
            return false;
        }

        Optional<Usuario> usuario = usuarioRepository.findByUsrRecPassCode(code);
        if (!usuario.isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.get().setUsr_rec_tkn("");
        usuario.get().setUsr_pass(encoder.encode(password));

        Usuario savedUser = usuarioRepository.save(usuario.get());
        return true;
    }

    @Override
    public void uploadUserCv(String jwt, MultipartFile cv) throws IOException {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return;
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()) {
            return;
        }
        String filePath = fileService.saveFile(cv);
        Usuario usuario = usuarioOpt.get();
        usuario.setCvPath(filePath);
        usuarioRepository.save(usuario);

    }

    @Override
    public void pedirRestaurarPassword(Usuario usuarioEntrante) throws MessagingException, UnsupportedEncodingException {

        String email = usuarioEntrante.getUsr_email();

        Optional<Usuario> usuario = usuarioRepository.findByUsrEmail(email);
        if (!usuario.isPresent()) {
            return;
        }
        usuario.get().setUsr_rec_tkn(UUID.randomUUID().toString());

        Usuario usuarioActualizado = usuarioRepository.save(usuario.get());

        if (!usuarioActualizado.getUsr_email().equals(email)) {
            return;
        }
        emailService.sendRecoverPassword(usuarioActualizado);
    }


    @Override
    public Boolean actualizarUsuario(Usuario usuario, String jwt) {

        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return false;
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario usuarioExistente = usuarioOpt.get();

        if (usuario.getPais() != null) {
            if (usuario.getPais().getPais_id() != null) {
                Pais pais = new Pais();
                pais.setPais_id(usuario.getPais().getPais_id());
                usuarioExistente.setPais(pais);
            }
        }

        if (usuario.getUsr_ap_mat() != null) {
            usuarioExistente.setUsr_ap_mat(usuario.getUsr_ap_mat());
        }

        if (usuario.getUsr_ap_pat() != null) {
            usuarioExistente.setUsr_ap_pat(usuario.getUsr_ap_pat());
        }

        if (usuario.getUsr_nom() != null) {
            usuarioExistente.setUsr_nom(usuario.getUsr_nom());
        }

        if (usuario.getUsr_rut() != null) {
            usuarioExistente.setUsr_rut(usuario.getUsr_rut());
        }

        if (usuario.getUsr_tel() != null) {
            usuarioExistente.setUsr_tel(usuario.getUsr_tel());
        }
        if (usuario.getUsr_url_link() != null) {
            usuarioExistente.setUsr_url_link(usuario.getUsr_url_link());
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return true;
    }


    @Override
    public Resource getCVByUser(String jwt) throws IOException,
            EntityNotFoundException, MissingJwtException {

        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            throw new MissingJwtException("There is no jwt");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()) {
            throw new EntityNotFoundException("User is not present");
        }

        Usuario usuarioExistente = usuarioOpt.get();

        return fileService.getCV(usuarioExistente.getCvPath());

    }

    public Usuario actualizarUsuarioId(Long usuarioId, Usuario usuario){
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NoSuchElementException("El user con ID " + usuarioId + " no se encontro.")
        );

        usuarioExistente.setPais(usuario.getPais());
        usuarioExistente.setUsr_ap_mat(usuario.getUsr_ap_mat());
        usuarioExistente.setUsr_ap_pat(usuario.getUsr_ap_pat());
        usuarioExistente.setUsr_email(usuario.getUsr_email());
        usuarioExistente.setUsr_nom(usuario.getUsr_nom());
        usuarioExistente.setUsr_pass(usuario.getUsr_pass());
        usuarioExistente.setUsr_rut(usuario.getUsr_rut());
        usuarioExistente.setUsr_tel(usuario.getUsr_tel());
        usuarioExistente.setUsr_url_link(usuario.getUsr_url_link());
        usuarioExistente.setUsr_rol(usuario.getUsr_rol());

        return usuarioRepository.save(usuarioExistente);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerDatosUsuario(String jwt) throws Exception {
        Optional<Usuario> userOptional = getUsuarioByToken(jwt);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        userOptional.get().setUsr_pass("");
        userOptional.get().setUsr_ver_code("");
        userOptional.get().setUsr_rec_tkn("");
        userOptional.get().setHerramientas(new ArrayList<>());

        return userOptional.get();
    }

    @Override
    public Optional<Usuario> getUsuarioByToken(String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return Optional.empty();
        }
        return usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
    }

    @Override
    public List<Usuario> obtenerUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuariosConHerramientas() {
        return usuarioRepository.findAllWhitHerramientas();
    }


    @Override
    public void eliminarUsuarioId(Long usuarioId) {

        Usuario usuario = new Usuario();
        usuario.setUsr_id(usuarioId);
        usuarioRepository.delete(usuario);

    }

    @Override
    public Usuario guardarAdmin(Usuario usuario) throws Exception {
        Long usrId = usuario.getUsr_id();

        if (usrId != null) {
            Optional<Usuario> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                throw new Exception("El usuario ya está presente");
            }
        }
        Pais pais = usuario.getPais();

        // Asignamos el objeto Pais obtenido al atributo pais de la entidad Usuario
        usuario.setPais(pais);

        // para usuarios con rol "ADMIN"
        usuario.setUsr_rol("ADMIN");
        usuario.setUsr_ver_code(UUID.randomUUID().toString());
        usuario.setUsr_is_ver(false);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.setUsr_pass(encoder.encode(usuario.getUsr_pass()));

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        emailService.sendVerificationEmail(nuevoUsuario);

        return nuevoUsuario;
    }

    @Override
    public Usuario guardarRec(Usuario usuario) throws Exception {
        Long usrId = usuario.getUsr_id();

        if (usrId != null) {
            Optional<Usuario> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                throw new Exception("El usuario ya está presente");
            }
        }
        Pais pais = usuario.getPais();

        // Asignamos el objeto Pais obtenido al atributo pais de la entidad Usuario
        usuario.setPais(pais);

        //para usuarios con rol "REC"
        usuario.setUsr_rol("REC");
        usuario.setUsr_ver_code(UUID.randomUUID().toString());
        usuario.setUsr_is_ver(false);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.setUsr_pass(encoder.encode(usuario.getUsr_pass()));

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        emailService.sendVerificationEmail(nuevoUsuario);

        return nuevoUsuario;
    }


}
