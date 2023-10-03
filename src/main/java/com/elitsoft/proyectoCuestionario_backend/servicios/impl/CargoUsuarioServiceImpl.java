package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final UsuarioService usuarioService;

    public CargoUsuarioServiceImpl(UsuarioRepository usuarioRepository,
                                   CargoUsuarioRepository cargoRepository,
                                   UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
        this.usuarioService = usuarioService;
    }
    
    @Override
    public Boolean guardarCargo(CargoUsuario cargo, String jwt) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioByToken(jwt);
        if(!usuarioOptional.isPresent()){
            return false;
        }
        cargo.setUsuario(usuarioOptional.get());

        cargoRepository.findByUsuario(usuarioOptional.get())
                .forEach((cargoRepository::delete));


        cargoRepository.save(cargo);
        return true;
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
