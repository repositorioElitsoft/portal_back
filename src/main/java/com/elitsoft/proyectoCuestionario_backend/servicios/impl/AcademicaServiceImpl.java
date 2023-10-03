package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.AcademicaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class AcademicaServiceImpl implements AcademicaService {
    
    private final AcademicaRepository academicaRepository;
    private final UsuarioRepository usuarioRepository;

    public AcademicaServiceImpl(AcademicaRepository academicaRepository, UsuarioRepository usuarioRepository) {
        this.academicaRepository = academicaRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    
    
    @Override
    public Academica guardarAcademica(Academica academica, Long usr_id) throws Exception {
        Usuario usuario = usuarioRepository.findById(usr_id).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        academica.setUsuario(usuario);

        return academicaRepository.save(academica);
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

    @Override
    public Boolean guardar_academica(Academica academica){
        academicaRepository.save(academica);
        return true;
    }
    @Override
    public Boolean remove_academica(Long academica_id) {
        academicaRepository.deleteById(academica_id);
        return true;

    }


}
