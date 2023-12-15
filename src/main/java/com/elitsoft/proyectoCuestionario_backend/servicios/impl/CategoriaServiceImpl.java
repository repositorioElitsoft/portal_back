
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
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
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Long categoriaId, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NoSuchElementException("La categoria con ID " + categoriaId + " no se encontro.")
        );

        categoriaExistente.setDescripcion(categoria.getDescripcion());
        categoriaExistente.setTitulo(categoria.getTitulo());

        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).get();
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        categoriaRepository.delete(categoria);
    }
}
