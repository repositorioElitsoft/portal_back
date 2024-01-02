
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Question;
import com.elitsoft.proyectoCuestionario_backend.repositories.QuestionRepository;
import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;

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
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question agregarPregunta(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question actualizarPregunta(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> obtenerPreguntas() {
        
        return (Set<Question>) questionRepository.findAll();
    }

    @Override
    public Question actualizarPreguntaId(Long preguntaId, Question question) {

        Question questionExistente = questionRepository.findById(preguntaId).orElseThrow(
                () -> new NoSuchElementException("La pregunta con ID " + preguntaId + " no se encontro.")
        );

        questionExistente.setContenido(question.getContenido());
        questionExistente.setOption1(question.getOption1());
        questionExistente.setOption2(question.getOption2());
        questionExistente.setOption3(question.getOption3());
        questionExistente.setOption4(question.getOption4());
        questionExistente.setPoints(question.getPoints());
        questionExistente.setAnswer(question.getAnswer());

        return questionRepository.save(questionExistente);
    }

    @Override
    public Question obtenerPregunta(Long preguntaId) {
        return questionRepository.findById(preguntaId).get();
    }


    @Override
    public void eliminarPregunta(Long preguntaId) {
        Question question = new Question();
        question.setId(preguntaId);
        questionRepository.delete(question);
    }

    @Override
    public Question listarPregunta(Long preguntaId) {
        return this.questionRepository.getOne(preguntaId);
    }
}
