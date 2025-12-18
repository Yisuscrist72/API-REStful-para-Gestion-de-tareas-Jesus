package com.lopezvina.dam.taskmanager.controller;

import com.lopezvina.dam.taskmanager.model.Task;
import com.lopezvina.dam.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lopezvina.dam.taskmanager.model.Priority;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /api/tasks: Crear nueva tarea
    @PostMapping
    // Devuelve ResponseEntity<Task> y establece explícitamente el estado 201 (CREATED)
    // Usamos @Valid para activar las validaciones
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // GET /api/tasks: Obtener todas las tareas
    @GetMapping
    // Devuelve ResponseEntity<List<Task>> y establece el estado 200 (OK)
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks); // Equivalente a .status(HttpStatus.OK).body(tasks)
    }

    // GET /api/tasks/{id}: Obtener tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    // PUT /api/tasks/{id}: Actualizar tarea completa por ID
    @PutMapping("/{id}")
    // @RequestBody: Mapea el cuerpo JSON de la petición al objeto Task.
    // @Valid: Activa las validaciones de campos.
    public ResponseEntity<Task> updateTask(@PathVariable String id, @Valid @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    // DELETE /api/tasks/{id}: Eliminar tarea por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Elimina una tarea por ID. El código 204 No Content confirma la eliminación sin cuerpo de respuesta.
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    // PATCH /api/tasks/{id}/complete: Marcar tarea como completada
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable String id) {
        Task task = taskService.markTaskAsCompleted(id);
        return ResponseEntity.ok(task);
    }

    // PATCH /api/tasks/{id}/incomplete: Marcar tarea como incompleta
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<Task> markTaskAsIncomplete(@PathVariable String id) {
        Task task = taskService.markTaskAsIncomplete(id);
        return ResponseEntity.ok(task);
    }

    // --------- Endpoints de Búsqueda Personalizada ----------

    // GET /api/tasks/completed: Obtener tareas completadas
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getTasksByCompleted() {
        return ResponseEntity.ok(taskService.findByCompleted(true));
    }

    // GET /api/tasks/pending: Obtener tareas pendientes
    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        return ResponseEntity.ok(taskService.findByCompleted(false));
    }

    // GET /api/tasks/category/{category}: Buscar por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(taskService.findByCategory(category));
    }

    // GET /api/tasks/tag/{tag}: Buscar por etiqueta
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Task>> getTasksByTag(@PathVariable String tag) {
        return ResponseEntity.ok(taskService.findByTag(tag));
    }

    // GET /api/tasks/priority/{priority}: Buscar por prioridad
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(taskService.findByPriority(priority));
    }

    // GET /api/tasks/search?title={title}: Buscar por título
    @GetMapping("/search")
    public ResponseEntity<List<Task>> getTasksByTitleContaining(@RequestParam String title) {
        return ResponseEntity.ok(taskService.findByTitleContaining(title));
    }

    // GET /api/tasks/due?before={date} : Buscar por fecha de vencimiento
    @GetMapping("/due")
    public ResponseEntity<List<Task>> getTasksByDueDateBefore(@RequestParam String before) {
        return ResponseEntity.ok(taskService.findByDueDateBefore(java.time.LocalDateTime.parse(before)));
    }
}
