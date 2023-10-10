
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * @author ELITSOFT86
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
    public ResponseEntity<List<Examen>> listarExamenes(){
        return new ResponseEntity<>(examenService.obtenerExamenes(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{exam_id}")
    public Examen listarExamen(@PathVariable("exam_id") Long exam_id){
        return examenService.obtenerExamen(exam_id);
    }

    @DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable("exam_id") Long exam_id){
        examenService.eliminarExamen(exam_id);
    }

    @GetMapping("/categoria/{cat_exam_id}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("cat_exam_id") Long cat_exam_id){
        Categoria categoria = new Categoria();
        categoria.setCat_exam_id(cat_exam_id);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

}
