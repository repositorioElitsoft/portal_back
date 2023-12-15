package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entidades.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioRepository extends JpaRepository <UserJob, Long>{
    
    // Método para guardar una cargo, asociado a un usuario
    UserJob save(UserJob cargo);

    // Método para obtener todos los cargos por usuario
    List<UserJob> findByUsuario(User user);

    // Método para listar todos los cargos postulados 
    List<UserJob> findAll();
    
}
