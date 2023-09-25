package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/academicas")
public class AcademicaController {
    
    @Autowired
    private final AcademicaService academicaService;

    public AcademicaController(AcademicaService academicaService) {
        this.academicaService = academicaService;
    }
    
    @PostMapping("/")
    public ResponseEntity<Academica> guardarHerramienta(@RequestBody Academica academica,
                                                           @RequestParam Long usr_id) {
        try {
            Academica savedAcademica = academicaService.guardarAcademica(academica, usr_id);
            return new ResponseEntity<>(savedAcademica, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<Academica>> obtenerAcademicasPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setUsr_id(usuarioId);
        List<Academica> academicas = academicaService.obtenerAcademicasPorUsuario(usuario);
        return new ResponseEntity<>(academicas, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Academica>> obtenerListaAcademicas() {
        List<Academica> academicas = academicaService.obtenerListaAcademicas();
        return new ResponseEntity<>(academicas, HttpStatus.OK);
    }
    
    @GetMapping("/estados-academicos-unicos")
    public ResponseEntity<List<String>> obtenerEstadosAcademicosUnicos() {
        List<String> estadosAcademicos = academicaService.obtenerEstadosAcademicosUnicos();
        return new ResponseEntity<>(estadosAcademicos, HttpStatus.OK);
    }
    
    
    
}
