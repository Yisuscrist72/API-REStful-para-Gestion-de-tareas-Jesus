package com.lopezvina.dam.taskmanager.service;

import com.lopezvina.dam.taskmanager.model.Task;
import com.lopezvina.dam.taskmanager.model.Priority;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    // Métodos CRUD básicos
    Task createTask(Task task); // Crear una tarea

    List<Task> getAllTasks(); // Obtener todas las tareas

    Task getTaskById(String id); // Obtener una tarea por ID

    Task updateTask(String id, Task taskDetails); // Actualizar una tarea

    void deleteTask(String id); // Eliminar una tarea

    // Métodos de búsqueda personalizada
    List<Task> findByCompleted(boolean completed);
    List<Task> findByCategory(String category);
    List<Task> findByTag(String tag);
    List<Task> findByPriority(Priority priority);
    List<Task> findByTitleContaining(String title);
    List<Task> findByDueDateBefore(LocalDateTime date);

    // Métodos para cambiar estado
    Task markTaskAsCompleted(String id);
    Task markTaskAsIncomplete(String id);
}