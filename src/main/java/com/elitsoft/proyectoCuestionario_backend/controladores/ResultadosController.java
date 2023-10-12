package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Resultados;
import com.elitsoft.proyectoCuestionario_backend.servicios.ResultadosService;
import com.elitsoft.proyectoCuestionario_backend.servicios.impl.ResultadosServiceImpl;
import org.apache.catalina.Store;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
@CrossOrigin("*")
public class ResultadosController {
    @Autowired
    private ResultadosService resultadosService;

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerResultados(){
        List<Resultados> resultados=resultadosService.obtenerResultados();
        return new ResponseEntity<>(resultados, HttpStatus.OK);

    }

    @PostMapping("/")
    public Boolean guardarResultados (@RequestBody Resultados resultados,
                                      @RequestHeader("Authorization") String jwt)  {
        resultadosService.guardarResultados(resultados, jwt);
        return true;
    }


}