
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;
import java.util.List;
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
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }

    @PutMapping("/actualizar/{examenId}")
    public ResponseEntity<Examen> actualizarExamen(@PathVariable Long examenId, @RequestBody Examen examen){
        Examen examenActualizado = examenService.actualizarExamen(examenId, examen);
        return ResponseEntity.ok(examenActualizado);
    }

    @GetMapping("/")
    public ResponseEntity<List<Examen>> listarExamenes(){
        return new ResponseEntity<>(examenService.obtenerExamenes(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{exam_id}")
    public Examen listarExamen(@PathVariable("exam_id") Long exam_id){
        return examenService.obtenerExamen(exam_id);
    }

    @DeleteMapping("/eliminar/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId){
        examenService.eliminarExamen(examenId);
    }

    @GetMapping("/categoria/{cat_exam_id}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("cat_exam_id") Long cat_exam_id){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(cat_exam_id);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

}
