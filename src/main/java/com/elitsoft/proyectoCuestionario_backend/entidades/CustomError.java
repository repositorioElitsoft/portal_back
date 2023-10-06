package com.elitsoft.proyectoCuestionario_backend.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Table(name = "NONE")
@Data
public class CustomError {
    private String error;

}
