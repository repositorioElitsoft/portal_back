
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    Optional <Usuario> findById(Long usr_id);
    @Query("SELECT u FROM Usuario u WHERE u.usr_ver_code = :code")
    Optional <Usuario> findByUsrVerCode(@Param("code") String code);
    @Query("SELECT u FROM Usuario u WHERE u.usr_email = :email")
    Optional<Usuario> findByUsrEmail(@Param("email") String username);
    
    
    
    
}
