package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface LaboralService {
    
    Boolean guardarLaborales(List<Laboral> laboral, String jwt) throws Exception;
    ///List<Laboral> obtenerListaLaboralPorUsuario(Usuario usuario);

    Boolean guardarLaboral(Laboral laboral, String jwt) throws Exception;

    Boolean actualizarLaboral(Long laboralId, Laboral laboral, String jwt) throws Exception;
    
    List<Laboral> obtenerListaLaboral(String jwt) throws Exception;

    Boolean deleteLaboral(Long laboralId, String jwt)throws Exception;

    void eliminarLaboralPorUsuario(Long usuarioId);


    Laboral obtenerLaboralPorId(Long laboralId, String jwt) throws Exception;


}
