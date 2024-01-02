
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.services.ExamCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class ExamCategoryController {

    @Autowired
    private ExamCategoryService examCategoryService;


    @PostMapping("/")
    public ResponseEntity<ExamCategory> guardarCategoria(@RequestBody ExamCategory examCategory){
        ExamCategory examCategoryGuardada = examCategoryService.agregarCategoria(examCategory);
        return ResponseEntity.ok(examCategoryGuardada);
    }

    //Petición para enlistar las categorias por ID
    @GetMapping("/{categoriaId}")
    public ExamCategory listarCategoriaPorId(@PathVariable("categoriaId") Long categoriaId){
        return examCategoryService.obtenerCategoria(categoriaId);
    }

    @GetMapping("/")
    public ResponseEntity<List<ExamCategory>> listarCategorias(){
        return ResponseEntity.ok(examCategoryService.obtenerCategorias());
    }


    @PutMapping("/actualizar/{categoriaId}")
    public ResponseEntity <ExamCategory> actualizarCategoria(@PathVariable Long categoriaId, @RequestBody ExamCategory examCategory){
        ExamCategory examCategoryActualizada = examCategoryService.actualizarCategoria(categoriaId, examCategory);
        return ResponseEntity.ok(examCategoryActualizada);
    }

    @DeleteMapping("/eliminar/{categoriaId}")
    public void eliminarCategoria(@PathVariable("categoriaId") Long categoriaId){
        examCategoryService.eliminarCategoria(categoriaId);
    }
}
