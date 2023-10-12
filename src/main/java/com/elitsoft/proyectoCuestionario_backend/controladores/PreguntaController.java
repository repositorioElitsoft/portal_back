
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;
import com.elitsoft.proyectoCuestionario_backend.servicios.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;


/**
 *
 * @author ELITSOFT86
 */
@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{exam_id}")
    public ResponseEntity<List<Pregunta>> listarPreguntasDelExamen(@PathVariable("exam_id") Long exam_id){
        List<Pregunta> preguntas = preguntaService.findByExamenId(exam_id);
        return ResponseEntity.ok(preguntas);
    }


    @GetMapping("/{prg_id}")
    public Pregunta listarPreguntaPorId(@PathVariable("prg_id") Long prg_id){
        return preguntaService.obtenerPregunta(prg_id);
    }

    @DeleteMapping("/{prg_id}")
    public void eliminarPregunta(@PathVariable("prg_id") Long prg_id){
        preguntaService.eliminarPregunta(prg_id);
    }

    /*@GetMapping("/examen/todos/{exam_id}")
    public ResponseEntity<?> listarPreguntaDelExamenComoAdministrador(@PathVariable("exam_id") Long exam_id){
        Examen examen = new Examen();
        examen.setExam_id(exam_id);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);
    }*/

    /*@PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;
        Integer intentosTotales = 0;

        for(Pregunta p : preguntas){
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPrg_id());
            if(pregunta.getPrg_resp().equals(p.getPrg_resp())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getExam_ptos_max())/preguntas.size();
                puntosMaximos += puntos;
            }
            if(p.getPrg_resp() != null){
                intentos++;
            }
            intentosTotales++;
        }

        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("intentos",intentos);
        respuestas.put("intentosTotales",intentosTotales);
        return ResponseEntity.ok(respuestas);
    }*/
}
