
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicaService {
    
    Boolean guardarAcademica(Academica academica, String jwt);

    List<Academica> obtenerAcademicasPorUsuario(Usuario usuario);
    
    List<Academica> obtenerListaAcademicas();
    
    List<String> obtenerEstadosAcademicosUnicos();
}
