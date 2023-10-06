package com.elitsoft.proyectoCuestionario_backend;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ProyectoCuestionarioBackendApplication implements CommandLineRunner{
    
    @Autowired
    private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCuestionarioBackendApplication.class, args);
	}

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    @Override
    public void run(String... args) throws Exception {
        
       /*  Usuario usuario = new Usuario();
        
        usuario.setUsername("admin");
        usuario.setPassword("123");
        
        Rol r = new Rol ();
        r.setRolId(1L);
        r.setrolNombre("ADMIN");
        
        Set<UsuarioRol> usuarioRoles = new HashSet<>(); 
        
        UsuarioRol usuarioRol = new UsuarioRol();
        
        usuarioRol.setRol(r);
        usuarioRol.setUsuario(usuario);
        
        usuarioRoles.add(usuarioRol);
        
        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
        System.out.println(usuarioGuardado.getUsername()); */
        
        
    }



}
