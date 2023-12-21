package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.ExamenUserCountRepository;
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
    private ExamenUserCountRepository examenUserCountRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<ExamResult> obtenerResultados() {
        List<ExamResult>resultados = examResultRepository.findAll();
        return resultados;

    }

    @Override
    public List<ExamResult> obtenerResultadosByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return examResultRepository.findByUser(user);
    }

    @Override
    public Boolean guardarResultados(ExamResult examResult, String jwt){
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        examResult.setUser(userOptional.get());
        ExamResult createdExamResult = examResultRepository.save(examResult);

        Optional<ExamUserCount> examenUserCountOptional = examenUserCountRepository.findByUserAndExam(userOptional.get(), examResult.getExam());
        ExamUserCount examUserCount = new ExamUserCount();
        if(examenUserCountOptional.isPresent()) {
            examUserCount = examenUserCountOptional.get();
            examUserCount.setCount(examUserCount.getCount() + 1);
        } else{
            examUserCount.setExam(examResult.getExam());
            examUserCount.setUser(userOptional.get());
            examUserCount.setCount(1);
        }
        examenUserCountRepository.save(examUserCount);
        return true;
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