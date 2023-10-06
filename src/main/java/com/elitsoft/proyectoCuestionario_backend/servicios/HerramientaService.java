package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;


/**
 *
 * @author Maeva Martínez
 */
public interface HerramientaService {
    
    Boolean guardarHerramientas(List<Herramienta> herramientas, String Jwt) throws Exception;

    List<Herramienta> obtenerListaHerramientasPorUsuario(String jwt);
    

    public Herramienta obtenerHerramienta(Long herr_usr_id)throws Exception ;
    
    List<Herramienta> obtenerHerramientasConProductosPorUsuario(Long usuarioId);
        
    
}
        
    

