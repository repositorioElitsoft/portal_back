package com.sistema.PortalElitsoft.Servicios.impl;

import com.sistema.PortalElitsoft.Entidades.Categoria;
import com.sistema.PortalElitsoft.Entidades.Examen;
import com.sistema.PortalElitsoft.Repository.ExamenRepository;
import com.sistema.PortalElitsoft.Servicios.ExamenService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Noelid Chávez
 */
@Service
public class ExamenServiceImpl implements ExamenService {
    
    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());

    }

    @Override
    public Examen obtenerExamen(Long exam_id) {
        return examenRepository.findById(exam_id).get();
    }

    @Override
    public void eliminarExamen(Long exam_id) {
        
        Examen examen = new Examen();
        
        examen.setExam_id(exam_id);
        
        examenRepository.delete(examen);

    }
    
    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivos() {
        return examenRepository.findByActivo(true); 
    }

    @Override
    public List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria,true);
    }
    
}
