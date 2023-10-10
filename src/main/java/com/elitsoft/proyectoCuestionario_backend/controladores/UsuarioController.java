
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CustomError;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;

/**
 *
 * @author Elitsoft83
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;

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

    /*
    @GetMapping("/sendTest")
    public void sendTestEmail(){
        emailService.sendSimpleMessage("felipe.diaz@elitsoft-chile.com","test","elitsoftrob@gmail.com");
    }*/

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
    public List<Map<String, String>> listarUsuariosConHerramientas(){
        List<Map<String, String>> usuariosConHerramientas = usuarioService.listarUsuariosConHerramientas();
        return usuariosConHerramientas;
    }

//    @DeleteMapping("/{usuarioId}")
//    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
//        usuarioService.eliminarUsuario(usuarioId);
//    }
}
