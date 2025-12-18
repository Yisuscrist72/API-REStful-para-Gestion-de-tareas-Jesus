package com.lopezvina.dam.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Proporciona getters, setters, etc.
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
