package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.servicios.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final String uploadDirectory = "./cvs";
    @Override
    public String saveFile(MultipartFile file) throws IOException {

        if (!isPDF(file)){
            throw new IOException("File is not PDF");
        }

        Path uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        File uploadDir = new File(uploadPath.toString());
        if (!uploadDir.exists()) {
            if(!uploadDir.mkdirs()){
                throw new IOException("Couldn't make folder in path: "+ uploadPath.toString());
            }
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetLocation = uploadPath.resolve(fileName);

        // Save the file to the server
        file.transferTo(targetLocation.toFile());
        return fileName;
    }

    @Override
    public Resource getCV(String fileName) throws IOException {

        Path filePath = Paths.get(uploadDirectory +"/"+fileName); // Change the path accordingly

        Resource cv = new UrlResource(filePath.toUri());
        if (cv.exists() && cv.isReadable()) {
            return cv;
        } else {
           throw new IOException("File not found");
        }
    }

    @Override
    public void deleteFile(String cvPath) {

    }

    @Override
    public void eliminarCVByUserId(Long userId) {

    }

    private boolean isPDF(MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("application/pdf");
    }

}
