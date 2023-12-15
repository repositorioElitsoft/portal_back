
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.JobPosition;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CargoElitsoftRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CargoElitsoftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CargoElitsoftServiceImpl implements CargoElitsoftService{
    
    @Autowired
    private final CargoElitsoftRepository cargoElitsoftRepository;

    public CargoElitsoftServiceImpl(CargoElitsoftRepository cargoElitsoftRepository) {
        this.cargoElitsoftRepository = cargoElitsoftRepository;
    }
    
    @Override
    public List<JobPosition> obtenerListaCargosElitsoft() {
        return cargoElitsoftRepository.findAll();
    }

    @Override
    public Boolean guardar_cargos(JobPosition cargo){
        cargoElitsoftRepository.save(cargo);
        return true;
    }

    @Override
    public Boolean remove_cargo(Long cargo) {
        cargoElitsoftRepository.deleteById(cargo);
        return true;

    }

}
