package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public Boolean guardarCargo(CargoUsuario cargo, String jwt, Date fechaPostulacion) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioByToken(jwt);
        if(!usuarioOptional.isPresent()){
            return false;
        }
        cargo.setUsuario(usuarioOptional.get());
        cargo.setFechaPostulacion(fechaPostulacion);

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

    @Override
    public CargoUsuario obtenerCargoUsuario(String jwt) throws Exception {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<CargoUsuario> cargoUsuario = cargoRepository.findByUsuario(userOptional.get());
        if(cargoUsuario == null){
            throw new EntityNotFoundException();
        }
        if(cargoUsuario.isEmpty()){
            return new CargoUsuario();
        }

        return cargoUsuario.get(0);
    }

    @Override
    public void eliminarCargoPorUsuario(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<CargoUsuario> cargos = cargoRepository.findByUsuario(usuario.get());
            cargoRepository.deleteAll(cargos);
        }
    }
    @Override
    public Boolean actualizarDisponibilidadLaboral(String disponibilidadLaboral, String jwt) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioByToken(jwt);
        if (!usuarioOptional.isPresent()) {
            return false;
        }

        CargoUsuario cargoUsuario = cargoRepository.findByUsuario(usuarioOptional.get()).stream()
                .findFirst()
                .orElse(new CargoUsuario());

        cargoUsuario.setDisponibilidad(disponibilidadLaboral);
        cargoRepository.save(cargoUsuario);
        return true;
    }

    @Override
    public Boolean postularCargo(Long usuarioId, Date fechaPostulacion) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (!usuarioOptional.isPresent()) {
            return false;
        }

        CargoUsuario cargoUsuario = new CargoUsuario();
        cargoUsuario.setUsuario(usuarioOptional.get());
        cargoUsuario.setFechaPostulacion(fechaPostulacion);

        cargoRepository.save(cargoUsuario);
        return true;
    }

}
