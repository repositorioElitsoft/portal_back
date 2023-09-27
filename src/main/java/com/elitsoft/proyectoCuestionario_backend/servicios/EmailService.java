package com.elitsoft.proyectoCuestionario_backend.servicios;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
