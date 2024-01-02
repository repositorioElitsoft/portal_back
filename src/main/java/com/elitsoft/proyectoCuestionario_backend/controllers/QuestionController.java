
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Question;
import com.elitsoft.proyectoCuestionario_backend.services.ExamService;
import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 *
 * @author ELITSOFT86
 */
@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @PostMapping("/")
    public ResponseEntity<Question> guardarPregunta(@RequestBody Question question){
        return ResponseEntity.ok(questionService.agregarPregunta(question));
    }

    @PutMapping("/actualizar/{preguntaId}")
    public ResponseEntity<Question> actualizarPregunta(@PathVariable Long preguntaId, @RequestBody Question question){
        Question questionActualizada = questionService.actualizarPreguntaId(preguntaId, question);
        return ResponseEntity.ok(questionActualizada);
    }


    @GetMapping("/examen/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("examenId") Long exam_id){
        Exam exam = examService.obtenerExamen(exam_id);
        List<Question> questions = exam.getQuestions();

        List examenes = new ArrayList(questions);
        if(examenes.size() > Integer.parseInt(exam.getQuestionNumber())){
            examenes = examenes.subList(0,Integer.parseInt(exam.getQuestionNumber() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes); 
    /*  Examen examen = examenService.obtenerExamen(examenId);
    Set<Pregunta> preguntas = examen.getPreguntas();

    // Crear una lista aleatoria de preguntas
    List<Pregunta> preguntasAleatorias = new ArrayList<>(preguntas);
    Collections.shuffle(preguntasAleatorias);

    // Limitar el número de preguntas
    int numPreguntas = Math.min(preguntasAleatorias.size(), 5);
    preguntasAleatorias = preguntasAleatorias.subList(0, numPreguntas);

    return ResponseEntity.ok(preguntasAleatorias);
      */
    }

    @GetMapping("/{preguntaId}")
    public Question listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return questionService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/eliminar/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        questionService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<List<Question>> listarPreguntaDelExamenComoAdministrador(@PathVariable("examenId") Long examenId){
        Exam exam = new Exam();
        exam.setId(examenId);
        List<Question> questions = questionService.obtenerPreguntasDelExamen(exam);
        return ResponseEntity.ok(questions);
    }

    /*@PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;
        Integer intentosTotales = 0;

        for(Pregunta p : preguntas){
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPreguntaId());
            if(pregunta.getRespuesta().equals(p.getRespuesta())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getPuntosMaximos())/preguntas.size();
                puntosMaximos += puntos;
            }
            if(p.getRespuesta() != null){
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
