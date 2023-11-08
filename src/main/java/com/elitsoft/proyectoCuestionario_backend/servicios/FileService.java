package com.elitsoft.proyectoCuestionario_backend.servicios;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public interface FileService {


    String saveFile(MultipartFile file) throws IOException;
    Resource getCV(String fileName) throws IOException;

    void deleteFile(String cvPath);


    void eliminarCVByUserId(Long userId);
}
