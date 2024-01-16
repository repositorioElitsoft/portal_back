package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.ExamResultRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ExamResultService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultServiceImpl implements ExamResultService {
    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public List<ExamResult> obtenerResultados() {
        List<ExamResult>resultados = examResultRepository.findAll();
        return resultados;
    }
    @Override
    public List<ExamResult> obtenerResultadosByUser(String jwt) {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            return null;
        }
        return examResultRepository.findByUser(userOptional.get());
    }

    @Override
    public ExamResult guardarResultados(ExamResult examResult, String jwt){
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return null;
        }
        examResult.setUser(userOptional.get());


        return examResultRepository.save(examResult);
    }

    @Override
    public void eliminarResultadosPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<ExamResult> resultados = examResultRepository.findByUser(usuario.get());
            examResultRepository.deleteAll(resultados);
        }
    }
}