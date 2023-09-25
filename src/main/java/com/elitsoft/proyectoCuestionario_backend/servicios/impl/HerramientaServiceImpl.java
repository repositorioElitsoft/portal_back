package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.HerramientaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Herramienta guardarHerramienta(Herramienta herramienta, Long usr_id) throws Exception {
        Usuario usuario = usuarioRepository.findById(usr_id).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        herramienta.setUsuario(usuario);

        return herramientaRepository.save(herramienta);
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
    
    
    //@Override
    //public List<Object[]> obtenerHerramientasConProductosPorUsuario(Long usuarioId) {
      //  return herramientaRepository.obtenerHerramientasConProductosPorUsuario(usuarioId);
    //}

   
}


