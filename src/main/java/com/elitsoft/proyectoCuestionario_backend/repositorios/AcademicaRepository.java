
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academic;
import com.elitsoft.proyectoCuestionario_backend.entidades.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Maeva Martínez
 */
public interface AcademicaRepository extends JpaRepository <Academic, Long> {
    
    // Método para guardar una inf. academica asociada a un usuario
    Academic save(Academic academic);
    
    // Método para obtener inf. academica por usuario
    List<Academic> findByUsuario(User user);

    // Método para listar todas las inf. academica
    List<Academic> findAll();
    
    //metodo para traer todos los estados de la tabla academica
    @Query("SELECT DISTINCT a.inf_acad_est FROM Academica a")
    List<String> findAllDistinctInfAcadEst();

    // para retornar un boolean
    //Boolean save(Academica academica);

    void deleteById(Long academica_id);


}
