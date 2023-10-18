package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.HerramientaService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martinez
 */

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public HerramientaServiceImpl(HerramientaRepository herramientaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.herramientaRepository = herramientaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
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

        Set<Long> idsEncontradas = new HashSet<>();

        for (Herramienta herramienta : herramientas){
            herramienta.setUsuario(usuarioOpt.get());
            herramientaRepository.save(herramienta);
            idsEncontradas.add(herramienta.getHerr_usr_id());
        }

        for (Herramienta herramientaAntigua : herramientasAntiguas){
                if(!idsEncontradas.contains(herramientaAntigua.getHerr_usr_id())){
                    herramientaRepository.deleteLaboralHerramientaReferences(herramientaAntigua.getHerr_usr_id());
                    herramientaRepository.delete(herramientaAntigua);
                }
        }




        return true;
    }



    @Override
    public List<Herramienta> obtenerListaHerramientasPorUsuario(String jwt) {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Herramienta> herramientas = herramientaRepository.findByUsuario(userOptional.get());
        if(herramientas == null){
            return Collections.emptyList();
        }

        return herramientas;
    }
    
    @Override
    public Herramienta obtenerHerramienta(Long herr_usr_id) {
        return herramientaRepository.findById(herr_usr_id).orElse(null);
    }
    
    
    @Override
    public List<Herramienta> obtenerHerramientasConProductosPorUsuario(Long usuarioId) {
        return herramientaRepository.obtenerHerramientasConProductosPorUsuario(usuarioId);
    }

    @Override
    public void eliminarHerramientaPorUsuario(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Herramienta> herramientas = herramientaRepository.findByUsuario(usuario.get());
            herramientaRepository.deleteAll(herramientas);
        }
    }

   
}


