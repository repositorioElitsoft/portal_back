
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Maeva Martínez
 */
public interface AcademicaRepository extends JpaRepository <Academica, Long> {
    
    // Método para guardar una inf. academica asociada a un usuario
    Academica save(Academica academica);
    
    // Método para obtener inf. academica por usuario
    List<Academica> findByUsuario(Usuario usuario);

    // Método para listar todas las inf. academica
    List<Academica> findAll();
    
    //metodo para traer todos los estados de la tabla academica
    @Query("SELECT DISTINCT a.inf_acad_est FROM Academica a")
    List<String> findAllDistinctInfAcadEst();
    
}
