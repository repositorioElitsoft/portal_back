package com.sistema.PortalElitsoft.Controladores;

import com.sistema.PortalElitsoft.Entidades.Rol;
import com.sistema.PortalElitsoft.Entidades.Usuario;
import com.sistema.PortalElitsoft.Entidades.UsuarioRol;
import com.sistema.PortalElitsoft.Servicios.UsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@CrossOrigin("*")
@CrossOrigin("http://localhost:4200") // Agrega aquí la URL de tu frontend
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRol_id(2L);
        rol.setRol_nom("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setTbl_usr(usuario);
        usuarioRol.setRol(rol);

        
        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }
  //  @GetMapping("/{usr_id}")
 //   public Usuario obtenerUsuariobyID(@PathVariable("usr_id") Long usr_id)throws Exception{
  //      return usuarioService.obtenerUsuariobyID(usr_id);
  //  }

 //      @GetMapping("/email/{email}")
 //      public Usuario obtenerUsuario(@PathVariable("email") String email) throws Exception{
//           return usuarioService.obtenerUsuario(email);
//           
 //     }
       
      @GetMapping("/email/{email}")
      public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("email") String email) throws Exception {
      Usuario usuario = usuarioService.obtenerUsuario(email);
      if (usuario != null) {
        String rol = usuarioService.obtenerRolUsuarioPorEmail(email);
        usuario.setRol(rol); // Asignar el nombre del rol al objeto Usuario
        return ResponseEntity.ok(usuario);
        } else {
        return ResponseEntity.notFound().build();
        }
     } 
       
      @PostMapping("/iniciar-sesion")
   public ResponseEntity<?> iniciarSesion(@RequestBody Map<String, String> credentials,
                                           HttpServletRequest request) {
        try {
            String email = credentials.get("email");
            String usr_pass = credentials.get("usr_pass");

            Usuario usuario = usuarioService.iniciarSesion(email, usr_pass);

            // Si las credenciales son válidas, crear la sesión utilizando HttpSession o el mecanismo de sesión que elijas.
            // Por ejemplo, podrías hacerlo así utilizando HttpSession:
            //httpServletRequest.getSession().setAttribute("usuario", usuario);
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
        }
    }
    
  /*  @GetMapping("/usuarios/email/{usr_email}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("email") String email) throws Exception {
        Usuario usuario = usuarioService.obtenerUsuario(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/{usr_id}")
    public void eliminarUsuario(@PathVariable("usr_id") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }
    
    
  //  @GetMapping("/")
  //  public ResponseEntity<?> listarUsuarios(){
 //       return ResponseEntity.ok(usuarioService.obtenerUsuarios());
 //   }
    
    @GetMapping("/")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.findAll();
    } 
}
