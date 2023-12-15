package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CargoUsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CargoUsuarioService;

import javax.persistence.EntityNotFoundException;

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
    public Boolean guardarCargo(UserJob cargo, String jwt, Date fechaPostulacion) throws Exception {

        Optional<User> usuarioOptional = usuarioService.getUsuarioByToken(jwt);
        if(!usuarioOptional.isPresent()){
            return false;
        }
        cargo.setUser(usuarioOptional.get());

        cargo.setFechaPostulacion(fechaPostulacion);

        cargoRepository.findByUsuario(usuarioOptional.get())
                .forEach((cargoRepository::delete));


        cargoRepository.save(cargo);
        return true;
    }


    @Override
    public List<UserJob> obtenerCargosPorUsuario(User usr_id) {
        return cargoRepository.findByUsuario(usr_id);
    }

    @Override
    public List<UserJob> obtenerListaCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public UserJob obtenerCargoUsuario(String jwt) throws Exception {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<UserJob> userJob = cargoRepository.findByUsuario(userOptional.get());
        if(userJob == null){
            throw new EntityNotFoundException();
        }
        if(userJob.isEmpty()){
            return new UserJob();
        }

        return userJob.get(0);
    }

    @Override
    public void eliminarCargoPorUsuario(Long usuarioId) {
        Optional<User> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<UserJob> cargos = cargoRepository.findByUsuario(usuario.get());
            cargoRepository.deleteAll(cargos);
        }
    }
    @Override
    public Boolean actualizarDisponibilidadLaboral(String disponibilidadLaboral, String jwt) throws Exception {
        Optional<User> usuarioOptional = usuarioService.getUsuarioByToken(jwt);
        if (!usuarioOptional.isPresent()) {
            return false;
        }

        UserJob userJob = cargoRepository.findByUsuario(usuarioOptional.get()).stream()
                .findFirst()
                .orElse(new UserJob());

        userJob.setDisponibilidad(disponibilidadLaboral);
        cargoRepository.save(userJob);
        return true;
    }


}
