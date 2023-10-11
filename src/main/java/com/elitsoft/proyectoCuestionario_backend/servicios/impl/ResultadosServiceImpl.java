package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Resultados;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
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
    private UsuarioService usuarioService;

    @Override
    public List<Resultados> obtenerResultados() {
        List<Resultados>resultados = resultadosRepository.findAll();
        return resultados;

    }

    @Override
    public Boolean guardarResultados(Resultados resultados, String jwt){
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        resultados.setUsuario(userOptional.get());
        resultadosRepository.save(resultados);
        return true;
    }


}