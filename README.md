📝 Task Manager API - Sistema de Gestión de Tareas
==================================================

🚀 Descripción del Proyecto
---------------------------

Esta es una API RESTful desarrollada con **Spring Boot 4.0** y **MongoDB** diseñada para la gestión eficiente de tareas. El sistema permite organizar actividades mediante un CRUD completo y ofrece características de validación, manejo de excepciones y auditoría automática.

El proyecto destaca por:

-   **Validación de datos:** Uso de `@NotBlank` para asegurar que las tareas siempre tengan título.
-   **Manejo de excepciones:** Respuestas personalizadas para errores 404 (No encontrado) y 400 (Validación).
-   **Auditoría automática:** Gestión de fechas de creación y actualización de forma automática.

🛠️ Requisitos Previos
----------------------

Para ejecutar este proyecto localmente, necesitas:

-   **Java 25** (JDK) o superior.
-   **MongoDB** (ejecutándose en el puerto por defecto: 27017).
-   **Maven 3.9+** (o usar el wrapper `./mvnw` incluido).
-   **Postman** (para importar la colección de pruebas).

⚙️ Instrucciones de Instalación
-------------------------------

1.  **Clonar el repositorio:**

    ```
    git clone <url-del-repositorio>
    cd task-manager
    ```

2.  **Configurar la base de datos:** Asegúrate de que el servicio de MongoDB esté activo. La base de datos se creará automáticamente según la configuración en `application.properties`.

3.  **Ejecutar la aplicación:**

    ```
    ./mvnw spring-boot:run
    ```

    La API estará disponible en: `http://localhost:8080/api/tasks`

### ⚠️ Solución de problemas: Puerto 8080 ocupado

Si al intentar arrancar la aplicación recibes un error indicando que el puerto **8080** ya está en uso, tienes dos opciones:

**Opción A: Liberar el puerto (Windows)**

1.  Abre la terminal (CMD o PowerShell) como administrador.
2.  Ejecuta: `netstat -ano | findstr :8080`
3.  Identifica el PID (número al final de la línea) y ejecuta: `taskkill /F /PID <número_PID>`

**Opción B: Cambiar el puerto del proyecto**

Edita el archivo `src/main/resources/application.properties` y añade la siguiente línea para usar otro puerto (ejemplo: 8081):

```
server.port=8081
```

📡 Documentación de Endpoints
-----------------------------

A continuación se listan los endpoints principales del API, organizados por tipo de operación.

1. Gestión Principal (CRUD)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/tasks` | Obtiene el listado de todas las tareas (10+ tareas de prueba). |
| POST   | `/api/tasks` | Crea una nueva tarea individual. |
| GET    | `/api/tasks/{id}` | Obtiene los detalles de una tarea específica por su ID. |
| PUT    | `/api/tasks/{id}` | Reemplaza una tarea completa por una nueva versión. |
| DELETE | `/api/tasks/{id}` | Elimina una tarea de forma permanente (Retorna 204 No Content). |

2. Filtros y Búsquedas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tasks/priority/{ALTA|MEDIA|BAJA}` | Filtra por nivel de prioridad. |
| GET | `/api/tasks/category/{nombre}` | Filtra por categoría (ej. Diseño, Desarrollo). |
| GET | `/api/tasks/tag/{etiqueta}` | Busca tareas que contengan una etiqueta específica. |
| GET | `/api/tasks/pending` | Lista todas las tareas con `completed: false`. |

3. Acciones de Estado (PATCH)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| PATCH | `/api/tasks/{id}/complete` | Marca una tarea como completada. |
| PATCH | `/api/tasks/{id}/incomplete` | Marca una tarea como pendiente (no completada). |

📄 Ejemplos de Petición y Respuesta (JSON)
------------------------------------------

### ✅ Ejemplo: Crear una Tarea (POST)

**Request Body:**

```json
{
    "title": "Configurar Seguridad JWT",
    "description": "Implementar tokens para proteger la API.",
    "priority": "ALTA",
    "category": "Desarrollo",
    "completed": false,
    "tags": ["seguridad", "backend"]
}
```

**Response (201 Created):**

```json
{
    "id": "694464c4e85b33c08c3a96af",
    "title": "Configurar Seguridad JWT",
    "completed": false,
    "createdAt": "2025-12-18T20:30:00",
    "updatedAt": "2025-12-18T20:30:00"
}
```

### ❌ Ejemplo: Error de Validación (400 Bad Request)

**Request:** `{"title": "", "priority": "BAJA"}`

**Response:**

```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed",
    "errors": [
        { "field": "title", "defaultMessage": "El título es obligatorio" }
    ]
}
```

🧪 Pruebas y Población de Datos (Postman)
-----------------------------------------

Se adjunta el archivo de colección de Postman exportado.

**Para evaluar el proyecto:**

1.  **Importar:** En Postman, cargar el archivo JSON de la colección.
2.  **Población de Datos:** En el endpoint `POST Crear Tarea`, se han guardado **10 Examples** con diferentes configuraciones de tareas.
3.  **Casos de Error:** Se incluyen peticiones para disparar errores 400 y 404 con sus respuestas guardadas para verificación.
