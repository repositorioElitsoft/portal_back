package com.elitsoft.proyectoCuestionario_backend.controladores;


import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;

import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;
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
@RequestMapping("/laboral")
public class LaboralController {
    
    private final LaboralService laboralService;

    @Autowired
    public LaboralController(LaboralService laboralService) {
        this.laboralService = laboralService;
    }
    
    @PostMapping("/multiple")
    public ResponseEntity<?> guardarLaborales(
            @RequestBody List<Laboral> laborales,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(laboralService.guardarLaborales(laborales, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarLaboral(
            @RequestBody Laboral laboral,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            laboralService.guardarLaboral(laboral, jwt);
            return new ResponseEntity<>( true ,HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{laborarId}")
    public ResponseEntity<?> actualizarLaboral(
            @PathVariable Long laborarId,
            @RequestBody Laboral laboral,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(laboralService.actualizarLaboral(laborarId, laboral, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
   public ResponseEntity<List<?>> obtenerListaLaboral(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Laboral> listaLaboral = laboralService.obtenerListaLaboral(jwt);
        return new ResponseEntity<>(listaLaboral, HttpStatus.OK);
    }

    @DeleteMapping("/{laboralId}")
    public ResponseEntity<Boolean> deleteLaboral(
            @PathVariable Long laboralId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return new ResponseEntity<>(laboralService.deleteLaboral(laboralId,jwt), HttpStatus.OK);
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


