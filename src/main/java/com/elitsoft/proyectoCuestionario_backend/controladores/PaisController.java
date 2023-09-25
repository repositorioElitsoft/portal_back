
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Pais;
import com.elitsoft.proyectoCuestionario_backend.servicios.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez 
 */
@RestController
@RequestMapping("/paises")
@CrossOrigin("*")
public class PaisController {
    
    @Autowired
    private PaisService paisService;
    
    @GetMapping("/{pais_id}")
    public Pais obtenerPaisId(@PathVariable("pais_id") Long pais_id){
        return paisService.obtenerPais(pais_id);
    }
    
    @GetMapping("/nombre-pais")
    public Pais obtenerPaisPorNombre(@RequestParam("pais_nom") String pais_nom) {
    return paisService.obtenerPaisPorNombre(pais_nom);
}
    
}
