
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */

@Service
public class ExamenServiceimpl implements ExamenService {
    
    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Long examenId, Examen examen) {

        Examen examenExistente = examenRepository.findById(examenId).orElseThrow(
                () -> new NoSuchElementException("El examen con ID " + examenId + " no se encontro.")
        );

        examenExistente.setTitulo(examen.getTitulo());
        examenExistente.setDescripcion(examen.getDescripcion());
        examenExistente.setPuntosMaximos(examen.getPuntosMaximos());
        examenExistente.setNumeroDePreguntas(examen.getNumeroDePreguntas());
        examenExistente.setCategoria(examen.getCategoria());
        examenExistente.setPreguntas(examen.getPreguntas());

        return examenRepository.save(examenExistente);
    }

    @Override
    public List<Examen> obtenerExamenes() {
        return (examenRepository.findAll());

    }

    @Override
    public Examen obtenerExamen(Long exam_id) {
        return examenRepository.findById(exam_id).get();
    }

    @Override
    public void eliminarExamen(Long examenId) {
        Optional<Examen> examen = examenRepository.findById(examenId);
        if (!examen.isPresent()){
            return;
        }
        examenRepository.delete(examen.get());
    }
    
    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    
}
