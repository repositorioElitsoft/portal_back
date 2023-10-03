
package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;
import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicaService {
    
    Academica guardarAcademica(Academica academica, Long usr_id) throws Exception;

    List<Academica> obtenerAcademicasPorUsuario(Usuario usuario);
    
    List<Academica> obtenerListaAcademicas();
    
    List<String> obtenerEstadosAcademicosUnicos();

    public Boolean guardar_academica(Academica academica);

    public Boolean remove_academica(Long academica_id);

}
