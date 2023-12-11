package com.elitsoft.proyectoCuestionario_backend.entidades.dto;

import lombok.Data;

import java.util.List;

@Data
public class MassiveEmailRequestDTO {
    private List<String> emails;
    private String subject;
}
