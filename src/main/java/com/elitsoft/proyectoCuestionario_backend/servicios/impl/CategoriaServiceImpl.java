
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaService;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CategoriaServiceImpl  implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria obtenerCategoria(Long cat_exam_id) {
        return categoriaRepository.findById(cat_exam_id).get();
    }

    @Override
    public void eliminarCategoria(Long cat_exam_id) {
        Categoria categoria = new Categoria();
        categoria.setCat_exam_id(cat_exam_id);
        categoriaRepository.delete(categoria);
    }
}
