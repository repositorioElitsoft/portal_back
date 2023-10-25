package com.elitsoft.proyectoCuestionario_backend.Exceptions;

public class MissingJwtException extends RuntimeException {
    public MissingJwtException(String message) {
        super(message);
    }
}