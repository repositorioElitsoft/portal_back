
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Certificado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface CertificadoRepository extends JpaRepository<Certificado, Long>{
    
    List<Certificado> findAll();
    
    @Query("SELECT c FROM Certificado c WHERE c.cert = :nombre")
    List<Certificado> findByNombre(@Param("nombre") String nombre);

    Certificado save(Certificado certificado);

    void deleteById(Long certificado);
}
