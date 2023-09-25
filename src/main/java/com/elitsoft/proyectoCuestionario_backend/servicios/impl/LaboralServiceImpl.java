package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.LaboralRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public LaboralServiceImpl(LaboralRepository laboralRepository, UsuarioRepository usuarioRepository, HerramientaRepository herramientaRepository) {
        this.laboralRepository = laboralRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    public Laboral guardarLaboral(Laboral laboral, Long usr_id, List<Long> herramientaIds) throws Exception {
        Usuario usuario = usuarioRepository.findById(usr_id).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        
        Set<Herramienta> herramientas = new HashSet<>();
        for (Long herr_usr_id : herramientaIds) {
            Herramienta herramienta = herramientaRepository.findById(herr_usr_id).orElse(null);
            if (herramienta == null) {
                throw new Exception("Herramienta no encontrada");
            }
            herramientas.add(herramienta);
        }
        
        laboral.setUsuario(usuario);
        laboral.setHerramientas(herramientas); // Establecer el conjunto de herramientas en laboral

        return laboralRepository.save(laboral);
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
