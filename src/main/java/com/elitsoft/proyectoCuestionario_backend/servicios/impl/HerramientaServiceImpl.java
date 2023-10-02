package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.HerramientaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martinez
 */

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public HerramientaServiceImpl(HerramientaRepository herramientaRepository, UsuarioRepository usuarioRepository) {
        this.herramientaRepository = herramientaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Boolean guardarHerramientas(List<Herramienta> herramientas, String Jwt) throws Exception {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(Jwt);
        if (token == null){
            return false;
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()){
            return false;
        }

        List<Herramienta> herramientasAntiguas = herramientaRepository.findByUsuario(usuarioOpt.get());

        for (Herramienta herramienta : herramientasAntiguas){
            herramientaRepository.delete(herramienta);
        }

        for (Herramienta herramienta : herramientas){
            herramienta.setUsuario(usuarioOpt.get());
            herramientaRepository.save(herramienta);
        }
        return true;
    }

    @Override
    public List<Herramienta> obtenerHerramientasPorUsuario(Usuario usr_id) {
        return herramientaRepository.findByUsuario(usr_id);
    }

    @Override
    public List<Herramienta> obtenerListaHerramientas() {
        return herramientaRepository.findAll();
    }
    
    @Override
    public Herramienta obtenerHerramienta(Long herr_usr_id) {
        return herramientaRepository.findById(herr_usr_id).orElse(null);
    }
    
    
    @Override
    public List<Herramienta> obtenerHerramientasConProductosPorUsuario(Long usuarioId) {
        return herramientaRepository.obtenerHerramientasConProductosPorUsuario(usuarioId);
    }

   
}


