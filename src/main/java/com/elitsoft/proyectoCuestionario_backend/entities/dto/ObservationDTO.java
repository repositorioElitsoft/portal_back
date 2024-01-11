package com.elitsoft.proyectoCuestionario_backend.entities.dto;


import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import lombok.Data;
import java.util.Date;

@Data
public class ObservationDTO {
    private Long id;
    private String description;
    private ResponsibleDTO author;
    private ResponsibleDTO lastUpdateResponsible;
}

