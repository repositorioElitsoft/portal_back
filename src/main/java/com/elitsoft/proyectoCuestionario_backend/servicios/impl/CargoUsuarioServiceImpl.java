package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CargoUsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CargoUsuarioService;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class CargoUsuarioServiceImpl implements CargoUsuarioService{
    
    private final UsuarioRepository usuarioRepository;
    private final CargoUsuarioRepository cargoRepository;

    public CargoUsuarioServiceImpl(UsuarioRepository usuarioRepository, CargoUsuarioRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
    }
    
    @Override
    public CargoUsuario guardarCargo(CargoUsuario cargo, Long usr_id) throws Exception {
        Usuario usuario = usuarioRepository.findById(usr_id).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        cargo.setUsuario(usuario);

        return cargoRepository.save(cargo);
    }

    @Override
    public List<CargoUsuario> obtenerCargosPorUsuario(Usuario usr_id) {
        return cargoRepository.findByUsuario(usr_id);
    }

    @Override
    public List<CargoUsuario> obtenerListaCargos() {
        return cargoRepository.findAll();
    }
    
    
    
}
