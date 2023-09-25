/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.PortalElitsoft.Controladores;

import com.sistema.PortalElitsoft.Entidades.Categoria;
import com.sistema.PortalElitsoft.Entidades.Examen;
import com.sistema.PortalElitsoft.Servicios.ExamenService;
import java.util.List;
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

/**
 *
 * @author Noelid Chávez
 */
@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }

    @PutMapping("/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.actualizarExamen(examen));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }

    @GetMapping("/{exam_id}")
    public Examen listarExamen(@PathVariable("exam_id") Long exam_id){
        return examenService.obtenerExamen(exam_id);
    }

    @DeleteMapping("/{exam_id}")
    public void eliminarExamen(@PathVariable("exam_id") Long exam_id){
        examenService.eliminarExamen(exam_id);
    }

    @GetMapping("/categoria/{cat_exam_id}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("cat_exam_id") Long cat_exam_id){
        Categoria categoria = new Categoria();
        categoria.setCat_exam_id(cat_exam_id);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

    @GetMapping("/activo")
    public List<Examen> listarExamenesActivos(){
        return examenService.obtenerExamenesActivos();
    }

    @GetMapping("/categoria/activo/{cat_exam_id}")
    public List<Examen> listarExamenesActivosDeUnaCategoria(@PathVariable("cat_exam_id") Long cat_exam_id){
        Categoria categoria = new Categoria();
        categoria.setCat_exam_id(cat_exam_id);
    return examenService.obtenerExamenesActivosDeUnaCategoria(categoria);
    }
}
