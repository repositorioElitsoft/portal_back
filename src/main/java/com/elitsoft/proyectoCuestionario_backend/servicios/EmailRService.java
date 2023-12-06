package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailRService {

    public void enviarCorreo(List<String> toEmails, String subject, String body, String motivo);
}
