
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicaService {

    Boolean guardarListaAcademicas(List<Academica> academicas, String jwt) throws Exception;
    ///List<Laboral> obtenerListaLaboralPorUsuario(Usuario usuario);

    Boolean guardarAcademica(Academica academica, String jwt) throws Exception;

    List<Academica> obtenerAcademicasPorUsuario(Usuario usuario);

    List<Academica> obtenerListaAcademicas(String jwt) throws Exception;

    Boolean actualizarAcademica(Long academicaId, Academica academica, String jwt) throws Exception;
    
    List<String> obtenerEstadosAcademicosUnicos();

    Boolean deleteAcademica(Long academicaId, String jwt)throws Exception;
}
