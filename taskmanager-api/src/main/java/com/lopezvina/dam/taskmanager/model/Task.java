package com.lopezvina.dam.taskmanager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data // Proporciona getters, setters, toString, equals y hashCode (Lombok)
@NoArgsConstructor // Genera un constructor sin argumentos (Lombok)
@AllArgsConstructor // Genera un constructor con todos los argumentos (Lombok)
@Document(collection = "tasks") // Marca la clase como un documento de MongoDB, con el nombre de colección "tasks"
public class Task {

    // Atributos

    @Id // Marca este campo como el identificador único
    private String id;

    @NotBlank // El título no puede ser nulo ni vacío
    @Size(max = 100) // Máximo 100 caracteres
    private String title;

    @Size(max = 500) // Máximo 500 caracteres para la descripción
    private String description;

    @NotNull // El estado no puede ser nulo
    private boolean completed;

    @NotNull // La prioridad no puede ser nula
    private Priority priority;

    private List<String> tags;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime dueDate;
}
