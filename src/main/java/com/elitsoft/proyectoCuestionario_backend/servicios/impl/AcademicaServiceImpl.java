package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.AcademicaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public Boolean guardarAcademica(Academica academica, String jwt)  {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return false;
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()){
            return false;
        }

        academica.setUsuario(usuarioOpt.get());

        academicaRepository.save(academica);
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
