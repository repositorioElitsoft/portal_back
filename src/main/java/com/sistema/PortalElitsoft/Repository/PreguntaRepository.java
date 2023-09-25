/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.PortalElitsoft.Repository;

import com.sistema.PortalElitsoft.Entidades.Examen;
import com.sistema.PortalElitsoft.Entidades.Pregunta;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Noelid Chávez
 */
public interface PreguntaRepository extends JpaRepository <Pregunta, Long>{
    
    Set<Pregunta> findByExamen(Examen examen);
    
}
