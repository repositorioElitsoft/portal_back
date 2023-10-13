package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.ExamenUserCount;
import com.elitsoft.proyectoCuestionario_backend.entidades.Resultados;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenUserCountRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ResultadosRepository;
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
        Usuario usuario = new Usuario();
        usuario.setUsr_id(userId);
        return resultadosRepository.findByUsuario(usuario);
    }

    @Override
    public Boolean guardarResultados(Resultados resultados, String jwt){
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        resultados.setUsuario(userOptional.get());
        Resultados createdResultados = resultadosRepository.save(resultados);

        Optional<ExamenUserCount> examenUserCountOptional = examenUserCountRepository.findByUsuarioAndExamen(userOptional.get(), resultados.getExamen());
        ExamenUserCount examenUserCount = new ExamenUserCount();
        if(examenUserCountOptional.isPresent()) {
            examenUserCount = examenUserCountOptional.get();
            examenUserCount.setCount(examenUserCount.getCount() + 1);
        } else{
            examenUserCount.setExamen(resultados.getExamen());
            examenUserCount.setUsuario(userOptional.get());
            examenUserCount.setCount(1);
        }
        examenUserCountRepository.save(examenUserCount);

        return true;
    }


}