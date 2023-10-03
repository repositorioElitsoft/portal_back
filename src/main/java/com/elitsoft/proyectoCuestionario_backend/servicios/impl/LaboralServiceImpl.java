package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.LaboralRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class LaboralServiceImpl implements LaboralService {
    
    private final LaboralRepository laboralRepository;
    private final UsuarioRepository usuarioRepository;
    private final HerramientaRepository herramientaRepository;
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public LaboralServiceImpl(LaboralRepository laboralRepository,
                              UsuarioRepository usuarioRepository,
                              HerramientaRepository herramientaRepository,
                              UsuarioService usuarioService) {
        this.laboralRepository = laboralRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Boolean guardarLaborales(List<Laboral> laborales, String jwt) throws Exception {

        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        laboralRepository.findByUsuario(userOptional.get())
                .forEach((laboralRepository::delete));

        laborales.forEach(laboral -> {
            laboral.setUsuario(userOptional.get());
            laboralRepository.save(laboral);
        });


        return true;
    }

    @Override
    public Boolean guardarLaboral(Laboral laboral, String jwt) throws Exception {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        laboral.setUsuario(userOptional.get());
        laboralRepository.save(laboral);
        return true;
    }

    @Override
    public List<Laboral> obtenerListaLaboral(String jwt) throws Exception{
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Laboral> laborales = laboralRepository.findByUsuario(userOptional.get());
        if(laborales == null){
            return Collections.emptyList();
        }

        return laborales;
    }


//    @Override
//    public List<Laboral> obtenerListaLaboralPorUsuario(Usuario usuario) {
//        return laboralRepository.findByUsuario(usuario);
//    }
//
//    @Override
//    public List<Laboral> obtenerListaLaboral() {
//        return laboralRepository.findAll();
//    }

//    @Override
//    public List<Laboral> obtenerLaboralesConHerramientasYProductosPorUsuario(Long usr_id) {
//        Usuario usuario = usuarioRepository.findById(usr_id).orElse(null);
//        if (usuario == null) {
//            throw new IllegalArgumentException("Usuario no encontrado");
//        }
//
//        return laboralRepository.findLaboralesWithHerramientasAndProductosByUsuario(usuario);
//    }

  

    
    
}
