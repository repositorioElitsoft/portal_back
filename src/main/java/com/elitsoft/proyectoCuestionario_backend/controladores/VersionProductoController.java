package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.VersionProducto;
import com.elitsoft.proyectoCuestionario_backend.servicios.ProductoService;
import com.elitsoft.proyectoCuestionario_backend.servicios.VersionProductoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/versiones-producto")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class VersionProductoController {

    @Autowired
    private final VersionProductoService versionProductoService;

    @Autowired
    public VersionProductoController(VersionProductoService versionProductoService) {
        this.versionProductoService = versionProductoService;
    }

    @GetMapping("/por-producto/{productoId}")
    public ResponseEntity<List<VersionProducto>> obtenerVersionesPorProducto(@PathVariable Long productoId) throws Exception {
        List<VersionProducto> versiones = versionProductoService.findByProductoId(productoId);
        return ResponseEntity.ok(versiones);
    }
}
