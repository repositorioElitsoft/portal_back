package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
    public void sendVerificationEmail(Usuario user) throws MessagingException, UnsupportedEncodingException;
    public void sendRecoverPassword(Usuario usuario) throws MessagingException, UnsupportedEncodingException;
}
