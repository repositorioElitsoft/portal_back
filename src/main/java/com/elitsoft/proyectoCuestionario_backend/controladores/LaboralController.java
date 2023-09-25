package com.elitsoft.proyectoCuestionario_backend.controladores;


import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;

import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/laboral")
public class LaboralController {
    
    private final LaboralService laboralService;

    @Autowired
    public LaboralController(LaboralService laboralService) {
        this.laboralService = laboralService;
    }
    
    @PostMapping("/")
    public ResponseEntity<Laboral> guardarLaboral(
            @RequestBody Laboral laboral,
            @RequestParam Long usr_id,
            @RequestParam List<Long> herramientaIds // Cambio aquí: aceptar una lista de IDs de herramientas
    ) {
        try {
            Laboral laboralGuardada = laboralService.guardarLaboral(laboral, usr_id, herramientaIds);
            return new ResponseEntity<>(laboralGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/listar-por-usuario/{usuarioId}")
//    public ResponseEntity<List<Laboral>> obtenerListaLaboralPorUsuario(@PathVariable Long usuarioId) {
//        Usuario usuario = new Usuario();
//        usuario.setUsr_id(usuarioId);
//
//        List<Laboral> listaLaboral = laboralService.obtenerListaLaboralPorUsuario(usuario);
//        System.out.println(listaLaboral);
//
//        return new ResponseEntity<>(listaLaboral, HttpStatus.OK);
//    }

//    @GetMapping("/listar")
//    public ResponseEntity<List<Laboral>> obtenerListaLaboral() {
//        List<Laboral> listaLaboral = laboralService.obtenerListaLaboral();
//
//        return new ResponseEntity<>(listaLaboral, HttpStatus.OK);
//    }

//    @GetMapping("/listar-con-herramientas/{usuarioId}")
//    public ResponseEntity<List<Laboral>> obtenerLaboralesConHerramientasYProductosPorUsuario(@PathVariable Long usuarioId) {
//        try {
//            List<Laboral> laborales = laboralService.obtenerLaboralesConHerramientasYProductosPorUsuario(usuarioId);
//            return new ResponseEntity<>(laborales, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
}


