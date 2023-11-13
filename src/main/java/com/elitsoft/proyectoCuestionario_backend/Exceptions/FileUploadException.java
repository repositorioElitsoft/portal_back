package com.elitsoft.proyectoCuestionario_backend.Exceptions;

import com.elitsoft.proyectoCuestionario_backend.message.FileMessage;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
@ControllerAdvice
public class FileUploadException {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<FileMessage> maxSizeException(MaxUploadSizeExceededException exc){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new FileMessage("uno o mas archivos exceden el tamaño maximo"));
    }
}
