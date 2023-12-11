package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Email;
import com.elitsoft.proyectoCuestionario_backend.entidades.dto.MassiveEmailRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailRService {

    public void enviarCorreo(MassiveEmailRequestDTO massiveEmailRequestDTO);
}
