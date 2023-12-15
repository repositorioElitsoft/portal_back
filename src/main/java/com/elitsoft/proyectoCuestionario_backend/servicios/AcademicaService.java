
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academic;
import com.elitsoft.proyectoCuestionario_backend.entidades.User;

import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicaService {

    Boolean guardarListaAcademicas(List<Academic> academics, String jwt) throws Exception;

    Boolean guardarAcademica(Academic academic, String jwt) throws Exception;

    List<Academic> obtenerAcademicasPorUsuario(User user);

    List<Academic> obtenerListaAcademicas(String jwt) throws Exception;

    Academic obtenerAcademica(Long academicaId);

    Boolean actualizarAcademica(Long academicaId, Academic academic, String jwt) throws Exception;

    List<String> obtenerEstadosAcademicosUnicos();

    Boolean deleteAcademica(Long academicaId, String jwt) throws Exception;

    void eliminarAcademicasPorUsuario(Long usuarioId);
}