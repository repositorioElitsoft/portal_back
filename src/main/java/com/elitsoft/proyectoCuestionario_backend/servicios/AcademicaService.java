
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicaService {

    Boolean guardarListaAcademicas(List<Academica> academicas, String jwt) throws Exception;

    Boolean guardarAcademica(Academica academica, String jwt) throws Exception;

    List<Academica> obtenerAcademicasPorUsuario(Usuario usuario);

    List<Academica> obtenerListaAcademicas(String jwt) throws Exception;

    Academica obtenerAcademica(Long academicaId);

    Boolean actualizarAcademica(Long academicaId, Academica academica, String jwt) throws Exception;

    List<String> obtenerEstadosAcademicosUnicos();

    Boolean deleteAcademica(Long academicaId, String jwt) throws Exception;

    void eliminarAcademicasPorUsuario(Long usuarioId);
}