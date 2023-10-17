
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CustomError;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuario();
    }


    @PostMapping("/")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        try{
            usuarioService.guardarUsuario(usuario);
        }
        catch (DataAccessException ex){
            CustomError error = new CustomError();
            error.setError("El usuario ya existe.");
            return new ResponseEntity<CustomError>(error, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true ,HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario, @RequestHeader("Authorization") String Jwt){
        try {
            usuarioService.actualizarUsuario(usuario,Jwt);
        }
        catch (DataAccessException ex){
            return new ResponseEntity<>(false ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true ,HttpStatus.OK);
    }

    @GetMapping("/")
    public Usuario obtenerUsuario(@RequestHeader("Authorization") String jwt)throws Exception{
        return usuarioService.obtenerDatosUsuario(jwt);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Boolean> verificarUsuario(@RequestBody Map<String, String> requestData){
        if(usuarioService.verificarUsuario(requestData)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/pedir-restauracion-pass")
    public void pedirRestauracionPassword(@RequestBody Usuario usuario) throws MessagingException, UnsupportedEncodingException {
      usuarioService.pedirRestaurarPassword(usuario);
    }
    @PutMapping("/cambiar-password/{code}")
    public Boolean cambiarPassword(@PathVariable("code") String rec_code,@RequestBody Map<String,String> password){

        return usuarioService.cambiarPassword(rec_code, password.get("pass"));
    }

    @GetMapping("/usuarios-herramientas")
    public List<Usuario> listarUsuariosConHerramientas(){
        List<Usuario> usuariosConHerramientas = usuarioService.listarUsuariosConHerramientas();
        return usuariosConHerramientas;
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<String> eliminarUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        try {
            // Eliminar registros académicos del usuario
            academicaService.eliminarAcademicasPorUsuario(usuarioId);

            // Eliminar el usuario
            usuarioService.eliminarUsuarioId(usuarioId);

            return ResponseEntity.ok("Usuario y registros académicos eliminados con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<Usuario> actualizarUsuarioId(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
        Usuario usuarioActualizado = usuarioService.actualizarUsuarioId(usuarioId, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }


    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }


    @GetMapping("/email/{userEmail}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String userEmail){
        Usuario user = usuarioService.getUsuarioByEmail(userEmail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/{usuarioId}")
    public Usuario obtenerUsuarioId(@PathVariable Long usuarioId, @RequestBody Usuario usuario) throws Exception{
        return usuarioService.obtenerUsuarioId(usuarioId, usuario);
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
}
