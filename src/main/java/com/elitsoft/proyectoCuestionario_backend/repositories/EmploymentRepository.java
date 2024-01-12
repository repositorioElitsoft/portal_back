package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Employment;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface EmploymentRepository extends JpaRepository <Employment, Long>{
    
    // Método para guardar inf. laboral asociado a un usuario
    Employment save(Employment employment);

    // Método para listar toda la info laboral
    List<Employment> findAll();

    List<Employment> findByUser(User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_tools_emp WHERE tool_usr_id = :toolId", nativeQuery = true)
    void deleteToolsAssociation(Long toolId);
}
