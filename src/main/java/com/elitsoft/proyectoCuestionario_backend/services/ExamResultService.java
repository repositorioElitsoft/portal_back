package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.ExamResult;

import java.util.List;


public interface ExamResultService {

    List<ExamResult> obtenerResultados();
    List<ExamResult> obtenerResultadosByUser(String userId);

    public ExamResult guardarResultados(ExamResult examResult, String jwt);

    void eliminarResultadosPorUsuario(Long usuarioId);


}