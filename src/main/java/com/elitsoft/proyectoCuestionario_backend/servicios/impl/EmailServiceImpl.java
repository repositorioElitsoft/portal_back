package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    //SenderEmail no es el email real, sino el que aparecerá una vez se mande el mensaje.
    //Se puede colocar cualquiera.
    private final String senderEmail = "no-response@elitsoft-chile.com";
    private String verificationRoute = "http://localhost:8080/verificar?code=";

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-response@elitsoft-chile.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendVerificationEmail(Usuario user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        //implementar lógica de código

        String messageBody =
                "Gracias por registrarte en la plataforma de Elitsoft."
                + "Para verificar tu email haz click en el siguiente link: <br>"
                + "<a href='"+ verificationRoute + user.getUsr_ver_code() +"'> Verificar email </a>  <br>"
                + "Si no te has registrado y ignora este email.";

        helper.setFrom(senderEmail, "Equipo Elitsoft");
        helper.setTo(user.getUsr_email());
        helper.setSubject("Verificación de Email");
        helper.setText(messageBody, true);
        mailSender.send(message);



    }

    public void sendRecoverPassword(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}