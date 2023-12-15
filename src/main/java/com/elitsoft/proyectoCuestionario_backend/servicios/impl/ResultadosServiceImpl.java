package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenUserCountRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ResultadosRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ResultadosService;
import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadosServiceImpl implements ResultadosService {
    @Autowired
    private ResultadosRepository resultadosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ExamenUserCountRepository examenUserCountRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Resultados> obtenerResultados() {
        List<Resultados>resultados = resultadosRepository.findAll();
        return resultados;

    }

    @Override
    public List<Resultados> obtenerResultadosByUser(Long userId) {
        User user = new User();
        user.setUsr_id(userId);
        return resultadosRepository.findByUsuario(user);
    }

    @Override
    public Boolean guardarResultados(Resultados resultados, String jwt){
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        resultados.setUser(userOptional.get());
        Resultados createdResultados = resultadosRepository.save(resultados);

        Optional<ExamenUserCount> examenUserCountOptional = examenUserCountRepository.findByUsuarioAndExamen(userOptional.get(), resultados.getExamen());
        ExamenUserCount examenUserCount = new ExamenUserCount();
        if(examenUserCountOptional.isPresent()) {
            examenUserCount = examenUserCountOptional.get();
            examenUserCount.setCount(examenUserCount.getCount() + 1);
        } else{
            examenUserCount.setExamen(resultados.getExamen());
            examenUserCount.setUser(userOptional.get());
            examenUserCount.setCount(1);
        }
        examenUserCountRepository.save(examenUserCount);
        return true;
    }

    @Override
    public void eliminarResultadosPorUsuario(Long usuarioId) {
        Optional<User> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Resultados> resultados = resultadosRepository.findByUsuario(usuario.get());
            resultadosRepository.deleteAll(resultados);
        }
    }
}