
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria_Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PreguntaRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.PreguntaService;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta actualizarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public List<Pregunta> findByExamenId(Long exam_id) {
        Examen examen = examenRepository.findById(exam_id)
                .orElseThrow(() -> new IllegalArgumentException("Examen no encontrada con el ID: " + exam_id));
        return preguntaRepository.findByExamenId(exam_id);
    }

    @Override
    public Pregunta obtenerPregunta(Long prg_id) {
        return preguntaRepository.findById(prg_id).get();
    }

    /*@Override
    @Transactional
    public Set<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
        return preguntaRepository.findByExamen(examen);
    }*/

    @Override
    public void eliminarPregunta(Long prg_id) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPrg_id(prg_id);
        preguntaRepository.delete(pregunta);
    }

    @Override
    public Pregunta listarPregunta(Long prg_id) {
        return this.preguntaRepository.getOne(prg_id);
    }
}
