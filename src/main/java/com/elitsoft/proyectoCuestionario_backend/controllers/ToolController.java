
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Employment;
import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.FileContentDTO;
import com.elitsoft.proyectoCuestionario_backend.services.EmploymentService;
import com.elitsoft.proyectoCuestionario_backend.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/herramientas")
@CrossOrigin(origins = "http://localhost:4200")
public class ToolController {

    private final ToolService toolService;
    private final EmploymentService employmentService;
    
    @Autowired
    public ToolController(ToolService toolService, EmploymentService employmentService) {
        this.toolService = toolService;
        this.employmentService = employmentService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTool(
            @RequestBody CreateToolDTO tool,
            @RequestHeader("Authorization") String Jwt) throws Exception {
        try {
            toolService.createTool(tool, Jwt);
        }
        catch (ConstraintViolationException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerListaHerramientas(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Tool> tools = toolService.obtenerListaHerramientasPorUsuario(jwt);
        return new ResponseEntity<>(tools, HttpStatus.OK);
    }

    @DeleteMapping("/{toolId}")
    public ResponseEntity<?> actualizarLaboral(
            @PathVariable Long toolId,
            @RequestHeader("Authorization") String jwt
    ){
        return new ResponseEntity<>(toolService.deleteUserTool(toolId, jwt), HttpStatus.OK);
    }

    @PostMapping("/{toolId}/certification")
    public ResponseEntity<?> uploadCertification(@RequestParam("file") MultipartFile certification,
                                                   @PathVariable("toolId") Long toolId,
                                                   @RequestHeader("Authorization") String jwt){
        Tool tool = new Tool();
        try {
            tool = toolService.addToolCertification(toolId,certification, jwt);
        }
        catch (DataAccessException | IOException ex){
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tool,HttpStatus.OK);
    }

    @GetMapping("/certification/{certId}")
    public ResponseEntity<?> downloadToolCertification(
                                                 @PathVariable("certId") Long certId,
                                                 @RequestHeader("Authorization") String jwt){
        try {
            FileContentDTO fileContent = toolService.downloadCertification(certId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
            headers.setPragma(fileContent.getFileName());

            return new ResponseEntity<>(fileContent.getResource(),headers,HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("err",HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/{toolId}/certification/{certId}")
    public ResponseEntity<?> uploadCertification(
                                                 @PathVariable("certId") Long certId,
                                                 @PathVariable("toolId") Long toolId,
                                                 @RequestHeader("Authorization") String jwt){

        try {
            toolService.deleteToolCertification(toolId, certId, jwt);
        }
        catch (DataAccessException | IOException ex){
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<?> getToolsForExams(@RequestHeader("Authorization") String jwt){
        List<Tool> tools = toolService.getToolsForExams(jwt);
        return new ResponseEntity<List<Tool>>(tools, HttpStatus.OK);
    }

}

