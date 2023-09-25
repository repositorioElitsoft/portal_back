/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.PortalElitsoft.Servicios;

import com.sistema.PortalElitsoft.Entidades.Categoria;
import com.sistema.PortalElitsoft.Entidades.Examen;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Elitsoft83
 */
public interface ExamenService {
    
    Examen agregarExamen(Examen examen);
    
    Examen actualizarExamen (Examen examen);
    
    Set<Examen> obtenerExamenes();
    
    Examen obtenerExamen(Long exam_id);
    
    void eliminarExamen(Long exam_id);
    
    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);

    List<Examen> obtenerExamenesActivos();

    List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria);
   
    
  
    
}
