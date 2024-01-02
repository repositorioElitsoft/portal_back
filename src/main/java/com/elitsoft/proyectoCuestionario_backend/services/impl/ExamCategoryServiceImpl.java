
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.repositories.ExamCategoryRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ExamCategoryService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class ExamCategoryServiceImpl implements ExamCategoryService {

    @Autowired
    private ExamCategoryRepository examCategoryRepository;

    @Override
    public ExamCategory agregarCategoria(ExamCategory examCategory) {
        return examCategoryRepository.save(examCategory);
    }

    @Override
    public ExamCategory actualizarCategoria(Long categoriaId, ExamCategory examCategory) {
        ExamCategory examCategoryExistente = examCategoryRepository.findById(categoriaId).orElseThrow(
                () -> new NoSuchElementException("La categoria con ID " + categoriaId + " no se encontro.")
        );

        examCategoryExistente.setDescription(examCategory.getDescription());
        examCategoryExistente.setTitle(examCategory.getTitle());

        return examCategoryRepository.save(examCategoryExistente);
    }

    @Override
    public List<ExamCategory> obtenerCategorias() {
        return examCategoryRepository.findAll();
    }

    @Override
    public ExamCategory obtenerCategoria(Long categoriaId) {
        return examCategoryRepository.findById(categoriaId).get();
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        ExamCategory examCategory = new ExamCategory();
        examCategory.setId(categoriaId);
        examCategoryRepository.delete(examCategory);
    }
}
