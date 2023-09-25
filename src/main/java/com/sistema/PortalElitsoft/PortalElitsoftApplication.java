package com.sistema.PortalElitsoft;

import com.sistema.PortalElitsoft.Entidades.Rol;
import com.sistema.PortalElitsoft.Entidades.Usuario;
import com.sistema.PortalElitsoft.Entidades.UsuarioRol;
import com.sistema.PortalElitsoft.Servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.sistema.PortalElitsoft")
public class PortalElitsoftApplication implements CommandLineRunner{
    
    @Autowired
    private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(PortalElitsoftApplication.class, args);
	}
        
    @Override
    public void run(String... args) throws Exception {
        
//        Usuario usuario = new Usuario();
//        
//        usuario.setUsr_nom("admin");
//        usuario.setUsr_ap_pat("istra");
//        usuario.setUsr_ap_mat("dor");
//        usuario.setEmail("admin@ejemplo.com");
//        usuario.setUsr_pass("123");
//        
//               
//        Rol r = new Rol ();
//        r.setRol_id(1L);
//        r.setRol_nom("ADMIN");
//        
//        Set<UsuarioRol> usuarioRoles = new HashSet<>(); 
//        
//        UsuarioRol usuarioRol = new UsuarioRol();
//        
//        usuarioRol.setRol(r);
//        usuarioRol.setTbl_usr(usuario);
//        
//        usuarioRoles.add(usuarioRol);
//        
//        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
//        System.out.println(usuarioGuardado.getUsr_nom()); 
//        System.out.println(usuarioGuardado.getUsr_ap_pat());
//        System.out.println(usuarioGuardado.getUsr_ap_mat());
//        System.out.println(usuarioGuardado.getEmail()); 
//        System.out.println(usuarioGuardado.getUsr_pass()); 
        
        
    }

}