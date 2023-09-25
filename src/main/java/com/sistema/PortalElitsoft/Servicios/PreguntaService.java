/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.PortalElitsoft.Servicios;

import com.sistema.PortalElitsoft.Entidades.Examen;
import com.sistema.PortalElitsoft.Entidades.Pregunta;
import java.util.Set;

/**
 *
 * @author Elitsoft83
 */
public interface PreguntaService {
    
    Pregunta agregarPregunta(Pregunta pregunta);

    Pregunta actualizarPregunta(Pregunta pregunta);

    Set<Pregunta> obtenerPreguntas();

    Pregunta obtenerPregunta(Long prg_id);

    Set<Pregunta> obtenerPreguntasDelExamen(Examen examen);

    void eliminarPregunta(Long prg_id);

    Pregunta listarPregunta(Long prg_id);

    
}
