package com.elitsoft.proyectoCuestionario_backend.repositorios;


import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface LaboralRepository extends JpaRepository <Laboral, Long>{
    
    // Método para guardar inf. laboral asociado a un usuario
    Laboral save(Laboral laboral);

//    @Query("SELECT l FROM Laboral l LEFT JOIN FETCH l.herramientas WHERE l.usuario = :usuario")
//    List<Laboral> findByUsuario(@Param("usuario") Usuario usuario);

    // Método para listar toda la info laboral
    List<Laboral> findAll();

    List<Laboral> findByUsuario(Usuario usuario);

}
