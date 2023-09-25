package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
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
import com.elitsoft.proyectoCuestionario_backend.servicios.CargoUsuarioService;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/cargos")
public class CargoUsuarioController {
    
    @Autowired
    private final CargoUsuarioService cargoService;

    public CargoUsuarioController(CargoUsuarioService cargoService) {
        this.cargoService = cargoService;
    }
    
    

    @PostMapping("/")
    public ResponseEntity<CargoUsuario> guardarCargo(@RequestBody CargoUsuario cargo,
                                                           @RequestParam Long usr_id) {
        try {
            CargoUsuario savedCargo = cargoService.guardarCargo(cargo, usr_id);
            return new ResponseEntity<>(savedCargo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<CargoUsuario>> obtenerCargosPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setUsr_id(usuarioId);
        List<CargoUsuario> cargos = cargoService.obtenerCargosPorUsuario(usuario);
        return new ResponseEntity<>(cargos, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CargoUsuario>> obtenerListaCargos() {
        List<CargoUsuario> herramientas = cargoService.obtenerListaCargos();
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }
    
}
