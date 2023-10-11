package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Resultados;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ResultadosService {

    List<Resultados> obtenerResultados();

    public Boolean guardarResultados(Resultados resultados, String jwt);


}