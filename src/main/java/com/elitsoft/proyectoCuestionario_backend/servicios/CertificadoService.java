
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Certificado;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CertificadoService {
    
    List<Certificado> findAll();
    
    List<Certificado> findByNombre(String nombre);
    public Boolean guardar_certificado(Certificado certificado);
    public Boolean remove_certificado(Long certificado);
    
}
