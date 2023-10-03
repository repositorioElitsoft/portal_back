package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;

import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*public Boolean guardar_academica (@RequestBody Academica academica )  {

        academicaService.guardar_academica(academica);
        return  true;
    }
    */

    @DeleteMapping("/{academica_id}")
    public Boolean remove_academica(@PathVariable Long academica_id){
       academicaService.remove_academica(academica_id);
       return true;
    }



}
