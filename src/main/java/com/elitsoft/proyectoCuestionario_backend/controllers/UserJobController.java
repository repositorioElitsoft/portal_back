package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.User;

import java.util.Date;
import java.util.List;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.UserJobApprovalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elitsoft.proyectoCuestionario_backend.services.UserJobService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/userjob")
public class UserJobController {

    @Autowired
    private final UserJobService userJobService;

    public UserJobController(UserJobService userJobService) {
        this.userJobService = userJobService;
    }



    @PostMapping("/")
    public ResponseEntity<?> guardarCargo(@RequestBody UserJob cargo,
                                          @RequestHeader("Authorization") String jwt) {
        try {

            Date applicationDate = new Date();
            Boolean result = userJobService.guardarCargo(cargo, jwt, applicationDate);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }



    @GetMapping("/")
    public ResponseEntity<List<UserJob>> obtenerUnCargoPorUsuario(@RequestHeader("Authorization") String jwt) throws Exception {
        List <UserJob> userJob = (List<UserJob>) userJobService.obtenerCargoUsuario(jwt);
        return new ResponseEntity<>(userJob,HttpStatus.OK);
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<UserJob>> obtenerCargosPorUsuario(@PathVariable Long usuarioId) {
        User user = new User();
        user.setId(usuarioId);
        List<UserJob> cargos = userJobService.obtenerCargosPorUsuario(user);
        return new ResponseEntity<>(cargos, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserJob>> obtenerListaCargos() {
        List<UserJob> herramientas = userJobService.obtenerListaCargos();
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<?> eliminarCargosPorUsuario(@PathVariable Long usuarioId) {
        try {
            userJobService.eliminarCargoPorUsuario(usuarioId);
            return new ResponseEntity<>("Cargos eliminados exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar los cargos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar-postulacion/{postulacionId}")
    public ResponseEntity<Boolean> eliminarPostulacionPorId(@PathVariable Long postulacionId,
                                                            @RequestHeader("Authorization") String jwt) {
        try {
            boolean eliminacionExitosa = userJobService.eliminarPostulacionPorId(postulacionId, jwt);
            return new ResponseEntity<>(eliminacionExitosa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{positionId}")
    public ResponseEntity<String> actualizarCargo(
            @PathVariable Long positionId,
            @RequestBody UserJob cargo,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            Boolean resultado = userJobService.actualizarCargo(positionId, cargo, jwt);
            if (resultado) {
                return ResponseEntity.ok("{\"message\": \"Cargo actualizado con éxito\"}");
            } else {
                return ResponseEntity.badRequest().body("{\"message\": \"No se pudo actualizar el cargo\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error al actualizar el cargo\"}");
        }
    }

    @PostMapping("/{userJobId}/approve")
    public ResponseEntity<?> approveObservation(@RequestHeader("Authorization") String jwt,
                                                @RequestBody UserJobApprovalDTO userJobApprovalDTO,
                                                @PathVariable Long userJobId){
        return new ResponseEntity<>(userJobService.approveUserJob(userJobId, jwt, userJobApprovalDTO),HttpStatus.ACCEPTED);
    }
}