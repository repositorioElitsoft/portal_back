
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CityRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.FileService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.*;

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
    private CityRepository cityRepository;

    @Autowired
    private FileService fileService;

    @Override
    public User guardarUsuario(User user, Long cityId) throws Exception {
        Long usrId = user.getUsr_id();

        if (usrId != null) {
            Optional<User> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                System.out.println("El usuario ya existe");
                throw new Exception("El usuario ya está presente");
            }
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setUsr_pass(encoder.encode(user.getUsr_pass()));
        user.setUsr_ver_code(UUID.randomUUID().toString());

        user.setUsr_is_ver(false);
        user.setUsr_rol("GUEST");

        User nuevoUser = usuarioRepository.save(user);
        emailService.sendVerificationEmail(nuevoUser);

        return nuevoUser;
    }

    @Override
    public Boolean verificarUsuario(Map<String, String> body) {

        Optional<User> user = usuarioRepository.findByUsrVerCode(body.get("code"));

        if (!user.isPresent()) {
            return false;
        }

        User presentUser = user.get();
        presentUser.setUsr_is_ver(true);

        User savedUser = usuarioRepository.save(presentUser);

        return savedUser.getUsr_is_ver();
    }

    @Override
    public User getUsuarioByEmail(String email) {
        User user = usuarioRepository.findByUsrEmail(email).orElseGet(User::new);
        user.setUsr_pass("");
        user.setUsr_rec_tkn("");
        user.setUsr_ver_code("");
        //user.setCreatedAt(new Date());
       // user.setUpdatedAt(new Date());
        return user;
    }


    @Override //aqui
    public User obtenerUsuarioId(Long usr_id) {
        return usuarioRepository.findById(usr_id).orElse(null);
    }

    @Override
    public Boolean cambiarPassword(String code, String password) {

        if (code.isEmpty()) {
            return false;
        }

        Optional<User> usuario = usuarioRepository.findByUsrRecPassCode(code);
        if (!usuario.isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        usuario.get().setUsr_rec_tkn("");
        usuario.get().setUsr_pass(encoder.encode(password));

        User savedUser = usuarioRepository.save(usuario.get());
        return true;
    }

    @Override
    public void uploadUserCv(String jwt, MultipartFile cv) throws IOException {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return;
        }

        Optional<User> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()) {
            return;
        }
        String filePath = fileService.saveFile(cv);
        User user = usuarioOpt.get();
        user.setCvPath(filePath);
        usuarioRepository.save(user);

    }

    @Override
    public void pedirRestaurarPassword(User userEntrante) throws MessagingException, UnsupportedEncodingException {

        String email = userEntrante.getUsr_email();

        Optional<User> usuario = usuarioRepository.findByUsrEmail(email);
        if (!usuario.isPresent()) {
            return;
        }
        usuario.get().setUsr_rec_tkn(UUID.randomUUID().toString());

        User userActualizado = usuarioRepository.save(usuario.get());

        if (!userActualizado.getUsr_email().equals(email)) {
            return;
        }
        emailService.sendRecoverPassword(userActualizado);
    }


    @Override
    public Boolean actualizarUsuario(User user, String jwt, Long Id) {
        System.out.println("Iniciando actualización de usuario");

        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            System.out.println("Token JWT es nulo");
            return false;
        }

        Optional<User> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()) {
            System.out.println("Usuario no encontrado en la base de datos");
            return false;
        }

        User userExistente = usuarioOpt.get();



        if (user.getUsr_ap_mat() != null) {
            userExistente.setUsr_ap_mat(user.getUsr_ap_mat());
            System.out.println("Apellido Materno actualizado: " + user.getUsr_ap_mat());
        }

        if (user.getUsr_ap_pat() != null) {
            userExistente.setUsr_ap_pat(user.getUsr_ap_pat());
            System.out.println("Apellido Paterno actualizado: " + user.getUsr_ap_pat());
        }

        if (user.getUsr_nom() != null) {
            userExistente.setUsr_nom(user.getUsr_nom());
            System.out.println("Nombre actualizado: " + user.getUsr_ap_pat());
        }

        if (user.getUsr_rut() != null) {
            userExistente.setUsr_rut(user.getUsr_rut());
            System.out.println("RUT actualizado: " + user.getUsr_rut());
        }

        if (user.getUsr_tel() != null) {
            userExistente.setUsr_tel(user.getUsr_tel());
            System.out.println("Teléfono actualizado: " + user.getUsr_tel());
        }

        if (user.getCity() != null) {
            userExistente.setCity(user.getCity());
            System.out.println("Teléfono actualizado: " + user.getCity());
        }

        if (user.getUsr_direcc() != null) {
            userExistente.setUsr_direcc(user.getUsr_direcc());
            System.out.println("Dirección actualizada: " + user.getUsr_direcc());
        }

        if (user.getUsr_gen() != null) {
            userExistente.setUsr_gen(user.getUsr_gen());
            System.out.println("Género actualizado: " + user.getUsr_gen());
        }

        if (user.getUsr_url_link() != null) {
            userExistente.setUsr_url_link(user.getUsr_url_link());
            System.out.println("URL del enlace actualizado: " + user.getUsr_url_link());
        }

        User userActualizado = usuarioRepository.save(userExistente);
        System.out.println("Usuario actualizado con éxito");
        return true;
    }


    @Override
    public Resource getCVByUser(Long userId) throws IOException,
            EntityNotFoundException {


        Optional<User> usuario = usuarioRepository.findById(userId);
        if (!usuario.isPresent()){
            throw  new EntityNotFoundException("No user with that id");
        }

        return fileService.getCV(usuario.get().getCvPath());

    }

    public User actualizarUsuarioId(Long usuarioId, User user){
        User userExistente = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NoSuchElementException("El user con ID " + usuarioId + " no se encontro.")
        );



        userExistente.setUsr_ap_mat(user.getUsr_ap_mat());
        userExistente.setUsr_ap_pat(user.getUsr_ap_pat());
        userExistente.setUsr_email(user.getUsr_email());
        userExistente.setUsr_direcc(user.getUsr_direcc());
        userExistente.setCity(user.getCity());
        userExistente.setUsr_nom(user.getUsr_nom());
        userExistente.setUsr_pass(user.getUsr_pass());
        userExistente.setUsr_rut(user.getUsr_rut());
        userExistente.setUsr_tel(user.getUsr_tel());
        userExistente.setUsr_url_link(user.getUsr_url_link());
        userExistente.setUsr_rol(user.getUsr_rol());

        return usuarioRepository.save(userExistente);
    }

    public List<User> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public User obtenerDatosUsuario(String jwt) throws Exception {
        Optional<User> userOptional = getUsuarioByToken(jwt);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        User user = userOptional.get();


        // Cargar la relación City, State y Country
        /*City city = cityRepository.findById(cityId).orElse(null);
        if (city != null) {
            usuario.setCityId(cityId);

            // Ahora carga las relaciones de City con State y State con Country
            Hibernate.initialize(city.getState());
            if (city.getState() != null) {
                Hibernate.initialize(city.getState().getCountry());
            }
        }*/

        // Limpiar datos sensibles
        user.setUsr_pass("");
        user.setUsr_ver_code("");
        user.setUsr_rec_tkn("");
        user.setHerramientas(new ArrayList<>());


        return user;
    }

    @Override
    public Optional<User> getUsuarioByToken(String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return Optional.empty();
        }
        return usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
    }

    @Override
    public List<User> obtenerUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<User> listarUsuariosConHerramientas() {
        return usuarioRepository.findAllWhitHerramientas();
    }


    @Override
    public void eliminarUsuarioId(Long usuarioId) {

        User user = new User();
        user.setUsr_id(usuarioId);
        usuarioRepository.delete(user);

    }

    @Override
    public User guardarAdmin(User user) throws Exception {
        Long usrId = user.getUsr_id();

        if (usrId != null) {
            Optional<User> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                throw new Exception("El usuario ya está presente");
            }
        }


        // para usuarios con rol "ADMIN"
        user.setUsr_rol("ADMIN");
        user.setUsr_ver_code(UUID.randomUUID().toString());
        user.setUsr_is_ver(false);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setUsr_pass(encoder.encode(user.getUsr_pass()));

        User nuevoUser = usuarioRepository.save(user);
        emailService.sendVerificationEmail(nuevoUser);

        return nuevoUser;
    }

    @Override
    public User guardarRec(User user) throws Exception {
        Long usrId = user.getUsr_id();

        if (usrId != null) {
            Optional<User> usuarioLocal = usuarioRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                throw new Exception("El usuario ya está presente");
            }
        }


        //para usuarios con rol "REC"
        user.setUsr_rol("REC");
        user.setUsr_ver_code(UUID.randomUUID().toString());
        user.setUsr_is_ver(false);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setUsr_pass(encoder.encode(user.getUsr_pass()));

        User nuevoUser = usuarioRepository.save(user);
        emailService.sendVerificationEmail(nuevoUser);

        return nuevoUser;
    }



    @Override
    public void eliminarCVByUserId(Long userId) throws IOException {
        Optional<User> usuarioOpt = usuarioRepository.findById(userId);
        if (usuarioOpt.isPresent()) {
            User user = usuarioOpt.get();
            String cvPath = user.getCvPath();
            if (cvPath != null && !cvPath.isEmpty()) {
                fileService.deleteFile(cvPath); // Agregar lógica para eliminar el archivo
                user.setCvPath(null); // Establecer el campo del CV en null
                usuarioRepository.save(user);
            }
        }
    }

    @Override
    public void deleteFile(String filePath) {
        // Agrega la lógica para eliminar el archivo en el sistema de archivos
        // Esto dependerá de cómo almacenas tus archivos, por ejemplo, usando java.io.File o algún otro enfoque.
    }

    @Override
    public void eliminarCVByUser(Long userId) {

    }

    @Override
    public void eliminarCV(Long usuarioId) throws IOException, EntityNotFoundException {
        Optional<User> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        User user = usuarioOpt.get();
        String cvPath = user.getCvPath();

        if (cvPath != null && !cvPath.isEmpty()) {
            fileService.deleteFile(cvPath);
            user.setCvPath(null);
            usuarioRepository.save(user);
        } else {
            throw new EntityNotFoundException("El usuario no tiene un CV adjunto.");
        }
    }


}
