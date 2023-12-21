
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.ExamCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Exam;
import com.elitsoft.proyectoCuestionario_backend.services.ExamService;
import java.util.List;

import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ELITSOFT86
 */
@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamController {

    @Autowired
    private ExamService examService;
    private QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<Exam> guardarExamen(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.agregarExamen(exam));
    }

    @PutMapping("/actualizar/{examenId}")
    public ResponseEntity<Exam> actualizarExamen(@PathVariable Long examenId, @RequestBody Exam exam){
        Exam examActualizado = examService.actualizarExamen(examenId, exam);
        return ResponseEntity.ok(examActualizado);
    }

    @GetMapping("/")
    public ResponseEntity<List<Exam>> listarExamenes(){
        return new ResponseEntity<>(examService.obtenerExamenes(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Exam>> listarExamenesUsuario(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(examService.obtenerExamenesByUser(jwt), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{exam_id}")
    public Exam listarExamen(@PathVariable("exam_id") Long exam_id){
        return examService.obtenerExamen(exam_id);
    }

    @DeleteMapping("/eliminar/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId){
        examService.eliminarExamen(examenId);
    }

    @GetMapping("/categoria/{exam_cat_id}")
    public List<Exam> listarExamenesDeUnaCategoria(@PathVariable("exam_cat_id") Long examCategoryId){
        ExamCategory examCategory = new ExamCategory();
        examCategory.setId(examCategoryId);
        return examService.listarExamenesDeUnaCategoria(examCategory);
    }

}
