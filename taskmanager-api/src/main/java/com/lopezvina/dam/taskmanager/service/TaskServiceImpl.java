package com.lopezvina.dam.taskmanager.service;

import com.lopezvina.dam.taskmanager.model.Task;
import com.lopezvina.dam.taskmanager.model.Priority;
import com.lopezvina.dam.taskmanager.repository.TaskRepository;
import com.lopezvina.dam.taskmanager.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    // Inyección por constructor (Punto 4.3)
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        // Asegura que sea 'false' al crear, si no se especifica.
        if (task.isCompleted()) {
            task.setCompleted(false);
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String id) {
        // Lanza TaskNotFoundException si no se encuentra
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(String id, Task taskDetails) {
        Task task = getTaskById(id); // Obtiene y valida existencia

        // Actualizar campos
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setPriority(taskDetails.getPriority());
        task.setTags(taskDetails.getTags());
        task.setCategory(taskDetails.getCategory());
        task.setDueDate(taskDetails.getDueDate());

        // Gestionar el updatedAt automáticamente
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(String id) {
        // Validar si existe antes de eliminar
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    // Método privado para gestionar el estado de completado
    private Task toggleCompletedStatus(String id, boolean completed) {
        Task task = getTaskById(id);
        task.setCompleted(completed);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task markTaskAsCompleted(String id) {
        return toggleCompletedStatus(id, true);
    }

    @Override
    public Task markTaskAsIncomplete(String id) {
        return toggleCompletedStatus(id, false);
    }

    // --- Implementación de métodos de búsqueda ---

    @Override
    public List<Task> findByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    @Override
    public List<Task> findByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    @Override
    public List<Task> findByTag(String tag) {
        return taskRepository.findByTagsContaining(tag);
    }

    @Override
    public List<Task> findByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> findByTitleContaining(String title) {
        return List.of();
    }

    @Override
    public List<Task> findByDueDateBefore(LocalDateTime date) {
        return taskRepository.findByDueDateBefore(date);
    }
}