
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Rol;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        return usuarioService.guardarUsuario(usuario);
    }


    @GetMapping("/{usr_id}")
    public Usuario obtenerUsuario(@PathVariable("usr_id") Long usr_id)throws Exception{
        return usuarioService.obtenerUsuario(usr_id);
    }

    @GetMapping("/sendTest")
    public void sendTestEmail(){
        emailService.sendSimpleMessage("felipe.diaz@elitsoft-chile.com","test","elitsoftrob@gmail.com");
    }

//    @DeleteMapping("/{usuarioId}")
//    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
//        usuarioService.eliminarUsuario(usuarioId);
//    }
}
