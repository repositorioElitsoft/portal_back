
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.ExamCategory;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CategoriaRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CategoriaService;

import java.util.*;

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
    public ExamCategory agregarCategoria(ExamCategory examCategory) {
        return categoriaRepository.save(examCategory);
    }

    @Override
    public ExamCategory actualizarCategoria(Long categoriaId, ExamCategory examCategory) {
        ExamCategory examCategoryExistente = categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NoSuchElementException("La categoria con ID " + categoriaId + " no se encontro.")
        );

        examCategoryExistente.setDescripcion(examCategory.getDescripcion());
        examCategoryExistente.setTitulo(examCategory.getTitulo());

        return categoriaRepository.save(examCategoryExistente);
    }

    @Override
    public List<ExamCategory> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public ExamCategory obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).get();
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        ExamCategory examCategory = new ExamCategory();
        examCategory.setCategoriaId(categoriaId);
        categoriaRepository.delete(examCategory);
    }
}
