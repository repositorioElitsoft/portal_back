
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.Exceptions.MissingJwtException;
import com.elitsoft.proyectoCuestionario_backend.entidades.CustomError;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Elitsoft83
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class    UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AcademicaService academicaService;

    @Autowired
    private ResultadosService resultadosService;

    @Autowired
    private CargoUsuarioService cargoUsuarioService;

    @Autowired
    private LaboralService laboralService;

    @Autowired
    private HerramientaService herramientaService;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuario();
    }


    @PostMapping("/")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        try {
            usuarioService.guardarUsuario(usuario);
        } catch (DataAccessException ex) {
            CustomError error = new CustomError();
            error.setError("El usuario ya existe.");
            return new ResponseEntity<CustomError>(error, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario,
                                               @RequestHeader("Authorization") String Jwt) {
        try {
            usuarioService.actualizarUsuario(usuario, Jwt);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/file/{userId}")
    public ResponseEntity<?> getUserFile(@PathVariable("userId") Long userId) {
        try {
            Resource cv = usuarioService.getCVByUser(userId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<Resource>(cv, headers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/file")
    public ResponseEntity<?> actualizarUsuarioFile(@RequestParam("file") MultipartFile file,
                                                   @RequestHeader("Authorization") String jwt) {
        try {
            usuarioService.uploadUserCv(jwt, file);
        } catch (DataAccessException | IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/")
    public Usuario obtenerUsuario(@RequestHeader("Authorization") String jwt) throws Exception {
        return usuarioService.obtenerDatosUsuario(jwt);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Boolean> verificarUsuario(@RequestBody Map<String, String> requestData) {
        if (usuarioService.verificarUsuario(requestData)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/pedir-restauracion-pass")
    public void pedirRestauracionPassword(@RequestBody Usuario usuario) throws MessagingException, UnsupportedEncodingException {
        usuarioService.pedirRestaurarPassword(usuario);
    }

    @PutMapping("/cambiar-password/{code}")
    public Boolean cambiarPassword(@PathVariable("code") String rec_code, @RequestBody Map<String, String> password) {

        return usuarioService.cambiarPassword(rec_code, password.get("pass"));
    }

    @GetMapping("/usuarios-herramientas")
    public List<Usuario> listarUsuariosConHerramientas() {
        List<Usuario> usuariosConHerramientas = usuarioService.listarUsuariosConHerramientas();
        return usuariosConHerramientas;
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<Map<String, String>> eliminarUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        Map<String, String> response = new HashMap<>();

        try {
            resultadosService.eliminarResultadosPorUsuario(usuarioId);
            cargoUsuarioService.eliminarCargoPorUsuario(usuarioId);
            laboralService.eliminarLaboralPorUsuario(usuarioId);
            herramientaService.eliminarHerramientaPorUsuario(usuarioId);
            academicaService.eliminarAcademicasPorUsuario(usuarioId);
            usuarioService.eliminarUsuarioId(usuarioId);

            response.put("message", "Usuario eliminado con éxito.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (AccessDeniedException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }


    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<Usuario> actualizarUsuarioId(@PathVariable Long usuarioId,
                                                       @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuarioId(usuarioId, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }


    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }


    @GetMapping("/email/{userEmail}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String userEmail) {
        Usuario user = usuarioService.getUsuarioByEmail(userEmail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/{usuarioId}")
    public Usuario obtenerUsuarioId(@PathVariable Long usuarioId) throws Exception {
        return usuarioService.obtenerUsuarioId(usuarioId);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> guardarAdmin(@RequestBody Usuario usuario) throws Exception {
        try {
            Usuario nuevoUsuario = usuarioService.guardarAdmin(usuario);
            return new ResponseEntity<Usuario>(nuevoUsuario, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            CustomError error = new CustomError();
            error.setError("El usuario ya existe.");
            return new ResponseEntity<CustomError>(error, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/rec")
    public ResponseEntity<?> guardarRec(@RequestBody Usuario usuario) throws Exception {
        try {
            Usuario nuevoUsuario = usuarioService.guardarRec(usuario);
            return new ResponseEntity<Usuario>(nuevoUsuario, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            CustomError error = new CustomError();
            error.setError("El usuario ya existe.");
            return new ResponseEntity<CustomError>(error, HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/eliminar-cv/{usuarioId}")
    public ResponseEntity<Map<String, String>> eliminarCV(@PathVariable("usuarioId") Long usuarioId) {
        Map<String, String> response = new HashMap<>();

        try {
            usuarioService.eliminarCV(usuarioId);
            response.put("message", "CV eliminado con éxito.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (IOException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




}