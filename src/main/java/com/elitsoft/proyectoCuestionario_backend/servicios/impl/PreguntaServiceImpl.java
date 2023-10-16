
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PreguntaRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.PreguntaService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta actualizarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Set<Pregunta> obtenerPreguntas() {
        
        return (Set<Pregunta>) preguntaRepository.findAll();
    }

    @Override
    public Pregunta actualizarPreguntaId(Long preguntaId, Pregunta pregunta) {

        Pregunta preguntaExistente = preguntaRepository.findById(preguntaId).orElseThrow(
                () -> new NoSuchElementException("La pregunta con ID " + preguntaId + " no se encontro.")
        );

        preguntaExistente.setContenido(pregunta.getContenido());
        preguntaExistente.setOpcion1(pregunta.getOpcion1());
        preguntaExistente.setOpcion2(pregunta.getOpcion2());
        preguntaExistente.setOpcion3(pregunta.getOpcion3());
        preguntaExistente.setOpcion4(pregunta.getOpcion4());
        preguntaExistente.setPrg_ptje_prg(pregunta.getPrg_ptje_prg());
        preguntaExistente.setRespuesta(pregunta.getRespuesta());

        return preguntaRepository.save(preguntaExistente);
    }

    @Override
    public Pregunta obtenerPregunta(Long preguntaId) {
        return preguntaRepository.findById(preguntaId).get();
    }

    @Override
    public List<Pregunta> obtenerPreguntasDelExamen(Examen examen) { //admin
        return preguntaRepository.findByExamen(examen);
    }

    @Override
    public void eliminarPregunta(Long preguntaId) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPreguntaId(preguntaId);
        preguntaRepository.delete(pregunta);
    }

    @Override
    public Pregunta listarPregunta(Long preguntaId) {
        return this.preguntaRepository.getOne(preguntaId);
    }
}
