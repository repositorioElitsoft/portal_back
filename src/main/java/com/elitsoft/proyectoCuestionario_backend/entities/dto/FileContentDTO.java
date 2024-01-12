package com.elitsoft.proyectoCuestionario_backend.entities.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class FileContentDTO {
    private Resource resource;
    private String fileName;
}
