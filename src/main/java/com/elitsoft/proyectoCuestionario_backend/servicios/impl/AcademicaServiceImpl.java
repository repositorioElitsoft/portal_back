package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.AcademicaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class AcademicaServiceImpl implements AcademicaService {
    
    private final AcademicaRepository academicaRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UsuarioService usuarioService;

    public AcademicaServiceImpl(AcademicaRepository academicaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.academicaRepository = academicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }
    
    @Override
    public Boolean guardarAcademica(List<Academica> academicas, String jwt)  {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        academicaRepository.findByUsuario(userOptional.get())
                .forEach((academicaRepository::delete));

        academicas.forEach(academica -> {
            academica.setUsuario(userOptional.get());
            academicaRepository.save(academica);
        });


        return true;
    }
    
    @Override
    public List<Academica> obtenerAcademicasPorUsuario(Usuario usr_id) {
        return academicaRepository.findByUsuario(usr_id);
    }

    @Override
    public List<Academica> obtenerListaAcademicas() {
        return academicaRepository.findAll();
    }
    
    @Override
    public List<String> obtenerEstadosAcademicosUnicos() {
        return academicaRepository.findAllDistinctInfAcadEst();
    }
    
}
