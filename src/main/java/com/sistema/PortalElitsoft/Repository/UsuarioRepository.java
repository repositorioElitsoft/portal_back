package com.sistema.PortalElitsoft.Repository;

import com.sistema.PortalElitsoft.Entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Elitsoft83
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    Optional <Usuario> findById(Long usr_id);

    //public Usuario findByID(Long usr_id);

    public Usuario findByEmail(String email);

    List<Usuario> findAll();
}
