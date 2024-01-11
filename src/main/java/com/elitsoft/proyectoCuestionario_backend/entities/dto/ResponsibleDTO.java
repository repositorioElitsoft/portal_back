package com.elitsoft.proyectoCuestionario_backend.entities.dto;

import com.elitsoft.proyectoCuestionario_backend.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ResponsibleDTO {
    private String name;
    private String firstLastname;
    private String secondLastname;
    private String email;
    private Date date;

}
