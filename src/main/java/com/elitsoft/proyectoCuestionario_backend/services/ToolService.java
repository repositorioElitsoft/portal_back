package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Certification;
import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 *
 * @author Maeva Martínez
 */
public interface ToolService {

    Boolean deleteUserTool(Long toolId, String jwt);
    Boolean createTool(CreateToolDTO tool, String Jwt) throws Exception;

    List<Tool> obtenerListaHerramientasPorUsuario(String jwt);

    public Tool obtenerHerramienta(Long herr_usr_id)throws Exception ;
    
    List<Tool> obtenerHerramientasConProductosPorUsuario(Long usuarioId);

    void eliminarHerramientaPorUsuario(Long usuarioId);

    Tool addToolCertification(Long toolId, MultipartFile certification, String jwt) throws IOException;

    Boolean deleteToolCertification(Long toolId, Long certId, String jwt) throws IOException;
    
}
        
    

