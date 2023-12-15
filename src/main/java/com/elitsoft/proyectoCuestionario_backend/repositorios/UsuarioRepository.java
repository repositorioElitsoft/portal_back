
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.User;

import java.util.List;

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
public interface UsuarioRepository extends JpaRepository<User,Long> {
    
    Optional <User> findById(Long usr_id);
    @Query("SELECT u FROM Usuario u WHERE u.usr_ver_code = :code")
    Optional <User> findByUsrVerCode(@Param("code") String code);
    @Query("SELECT u FROM Usuario u WHERE u.usr_rec_tkn = :code")
    Optional <User> findByUsrRecPassCode(@Param("code") String code);
    @Query("SELECT u FROM Usuario u WHERE u.usr_email = :email")
    Optional<User> findByUsrEmail(@Param("email") String username);

    List<User> findAll();

    @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.herramientas")
    List<User> findAllWhitHerramientas();

    //@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.city WHERE u.usr_id = :id")
   // Usuario findByIdWithCity(@Param("id") Long id);





}
