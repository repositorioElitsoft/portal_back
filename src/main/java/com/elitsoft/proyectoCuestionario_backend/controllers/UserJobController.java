package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.User;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elitsoft.proyectoCuestionario_backend.services.UserJobService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/cargos")
public class UserJobController {
    
    @Autowired
    private final UserJobService cargoService;

    public UserJobController(UserJobService cargoService) {
        this.cargoService = cargoService;
    }



    @PostMapping("/")
    public ResponseEntity<?> guardarCargo(@RequestBody UserJob cargo,
                                          @RequestHeader("Authorization") String jwt) {
        try {
            Date fechaPostulacion = new Date();
            Boolean result = cargoService.guardarCargo(cargo, jwt, fechaPostulacion);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }



    @GetMapping("/")
    public ResponseEntity<UserJob> obtenerUnCargoPorUsuario(@RequestHeader("Authorization") String jwt) throws Exception {
        UserJob userJob = cargoService.obtenerCargoUsuario(jwt);
        return new ResponseEntity<>(userJob,HttpStatus.OK);
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<UserJob>> obtenerCargosPorUsuario(@PathVariable Long usuarioId) {
        User user = new User();
        user.setId(usuarioId);
        List<UserJob> cargos = cargoService.obtenerCargosPorUsuario(user);
        return new ResponseEntity<>(cargos, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserJob>> obtenerListaCargos() {
        List<UserJob> herramientas = cargoService.obtenerListaCargos();
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }

    @PutMapping("/disponibilidad-laboral")
    public ResponseEntity<?> actualizarDisponibilidadLaboral(@RequestBody Map<String, String> request,
                                                             @RequestHeader("Authorization") String jwt) {
        try {
            String disponibilidadLaboral = request.get("disponibilidadLaboral");
            Boolean result = cargoService.actualizarDisponibilidadLaboral(disponibilidadLaboral, jwt);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
