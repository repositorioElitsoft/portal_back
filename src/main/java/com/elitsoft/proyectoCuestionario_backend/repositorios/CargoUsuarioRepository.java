package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioRepository extends JpaRepository <CargoUsuario, Long>{
    
    // Método para guardar una cargo, asociado a un usuario
    CargoUsuario save(CargoUsuario cargo);

    // Método para obtener todos los cargos por usuario
    List<CargoUsuario> findByUsuario(Usuario usuario);

    // Método para listar todos los cargos postulados 
    List<CargoUsuario> findAll();
    
}
