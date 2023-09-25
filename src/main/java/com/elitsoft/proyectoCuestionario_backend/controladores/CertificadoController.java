
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Certificado;
import com.elitsoft.proyectoCuestionario_backend.servicios.CertificadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/certificados")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificadoController {

    @Autowired
    private final CertificadoService certificadoService;

    
    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    @GetMapping("/")
    public List<Certificado> obtenerTodosLosCertificados() {
        return certificadoService.findAll();
    }

    @GetMapping("/{nombre}")
    public List<Certificado> obtenerCertificadosPorNombre(@PathVariable String nombre) {
        return certificadoService.findByNombre(nombre);
    }

    
}
