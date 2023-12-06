package com.elitsoft.proyectoCuestionario_backend.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
public class Email {

    private String toEmail;
    private String subject;
    private String body;
    private String motivo;

    public Email() {
    }



}

