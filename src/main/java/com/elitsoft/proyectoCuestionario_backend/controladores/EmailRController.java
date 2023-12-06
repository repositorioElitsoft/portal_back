package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Email;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/emailR")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailRController {

    @Autowired
    private EmailRService emailRService;

    @PostMapping("/enviar-correo")
    public ResponseEntity<String> enviarCorreo(@RequestBody Email email) {
        try {
            List<String> toEmails = Collections.singletonList(email.getToEmail());

            // Llamar al método enviarCorreo del servicio
            emailRService.enviarCorreo(toEmails, email.getSubject(), email.getBody(), email.getMotivo());

            return ResponseEntity.ok("Correo enviado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo: " + e.getMessage());
        }
    }


}
