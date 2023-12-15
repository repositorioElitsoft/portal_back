
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Certificado;
import com.elitsoft.proyectoCuestionario_backend.servicios.CertificadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/")
    public Boolean guardar_certificado (@RequestBody Certificado certificado )  {

        certificadoService.guardar_certificado(certificado);
        return  true;
    }

    @DeleteMapping("/{certificado}")
    public Boolean remove_certificado(@PathVariable Long certificado){
        certificadoService.remove_certificado(certificado);
        return true;
    }
    
}
