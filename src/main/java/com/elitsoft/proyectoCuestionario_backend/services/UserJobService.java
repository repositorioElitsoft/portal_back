package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Academical;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJobApproval;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.UserJobApprovalDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Maeva Martínez
 */
public interface UserJobService {

    Boolean guardarCargo(UserJob cargo, String jwt, Date applicationDate) throws Exception;
    Boolean actualizarCargo(Long positionId, UserJob cargo, String jwt ) throws Exception;
    List<UserJob> obtenerCargosPorUsuario(User user);
    
    List<UserJob> obtenerListaCargos();

    List<UserJob> obtenerCargoUsuario(String jwt) throws Exception;

    void eliminarCargoPorUsuario(Long usuarioId);

    boolean eliminarPostulacionPorId(Long postulacionId, String jwt) throws Exception;


    List<UserJobApproval> approveUserJob(Long userJobId, String jwt, UserJobApprovalDTO userJobApprovalDTO);


}
