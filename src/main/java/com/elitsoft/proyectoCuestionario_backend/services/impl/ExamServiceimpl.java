package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.ExamRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ToolRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.QuestionRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ExamService;

import java.util.*;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author Maeva Martínez
 */

@Service
public class ExamServiceimpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public Exam agregarExamen(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam actualizarExamen(Long examenId, Exam exam) {
        Exam examExistente = examRepository.findById(examenId)
                .orElseThrow(() -> new NoSuchElementException("El examen con ID " + examenId + " no se encontró."));

        actualizarCampoSiNoNulo(examExistente::setTitle, exam.getTitle());
        actualizarCampoSiNoNulo(examExistente::setDescription, exam.getDescription());
        actualizarCampoSiNoNulo(examExistente::setMaxPoints, exam.getMaxPoints());
        actualizarCampoSiNoNulo(examExistente::setQuestionNumber, exam.getQuestionNumber());
        actualizarCampoSiNoNulo(examExistente::setCategory, exam.getCategory());
        actualizarCampoSiNoNulo(examExistente::setProducts, exam.getProducts());


        if (exam.getQuestions() != null) {
            Set<Long> remainingIds = new HashSet<>();
            exam.getQuestions().forEach(pregunta -> {
                if (pregunta.getId() != null) {
                    remainingIds.add(pregunta.getId());
                }
            });

            if (examExistente.getQuestions() != null) {
                examExistente.getQuestions().removeIf(preguntaVieja ->
                        preguntaVieja != null && preguntaVieja.getId() != null &&
                                !remainingIds.contains(preguntaVieja.getId()));
            }
            actualizarCampoSiNoNulo(examExistente::setQuestions, exam.getQuestions());
        }

        return examRepository.save(examExistente);
    }

    private <T> void actualizarCampoSiNoNulo(Consumer<T> setter, T valor) {
        if (valor != null) {
            setter.accept(valor);
        }
    }


    @Override
    public List<Exam> obtenerExamenes() {
        return examRepository.findAll();
    }

    @Override
    public Exam obtenerExamen(Long exam_id) {
        return examRepository.findById(exam_id).orElse(null);
    }

    @Override
    public void eliminarExamen(Long examenId) {
        examRepository.deleteById(examenId);
    }

    @Override
    public List<Exam> listarExamenesDeUnaCategoria(ExamCategory examCategory) {
        return examRepository.findByCategory(examCategory);
    }

    @Override
    public List<Exam> obtenerExamenesByUser(String jwt) {

        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return Collections.emptyList();
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()) {
            return Collections.emptyList();
        }
        ArrayList<Exam> examenes = new ArrayList<>();

        Set<Product> productsUsed = new HashSet<>();


        List<Tool> herrUsr = toolRepository.findByUser(usuarioOpt.get());

        herrUsr.forEach(herramienta -> {

            productsUsed.add(herramienta.getProductVersion().getProduct());
        });

        productsUsed.forEach(producto -> {
            List<Exam> examsByProduct = examRepository.findByProducts(producto);

            examenes.addAll(examsByProduct);
        });

        return new ArrayList<>(examenes);
    }
}