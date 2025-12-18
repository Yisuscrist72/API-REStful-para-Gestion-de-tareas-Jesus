<p>📝 Task Manager API - Sistema de Gestión de Tareas</p>
<p>🚀 Descripción del Proyecto</p>
<p>Esta es una API RESTful desarrollada con Spring Boot 4.0 y MongoDB
diseñada para la gestión eficiente de tareas. El sistema permite
organizar actividades mediante un CRUD completo y ofrece capacidades de
filtrado avanzado por prioridad, categoría, etiquetas y estado de
finalización.</p>
<p>El proyecto destaca por:</p>
<p>Validación de datos: Uso de <span class="citation"
data-cites="NotBlank">@NotBlank</span> para asegurar que las tareas
siempre tengan título.</p>
<p>Manejo de excepciones: Respuestas personalizadas para errores 404 (No
encontrado) y 400 (Validación).</p>
<p>Auditoría automática: Gestión de fechas de creación y actualización
de forma automática.</p>
<p>🛠️ Requisitos Previos</p>
<p>Para ejecutar este proyecto localmente, necesitas:</p>
<p>Java 25 (JDK) o superior.</p>
<p>MongoDB (ejecutándose en el puerto por defecto: 27017).</p>
<p>Maven 3.9+ (o usar el wrapper ./mvnw incluido).</p>
<p>Postman (para importar la colección de pruebas).</p>
<p>⚙️ Instrucciones de Instalación</p>
<p>Clonar el repositorio:</p>
<p>git clone <url-del-repositorio> cd task-manager</p>
<p>Configurar la base de datos: Asegúrate de que el servicio de MongoDB
esté activo. La base de datos se creará automáticamente según la
configuración en application.properties.</p>
<p>Ejecutar la aplicación:</p>
<p>./mvnw spring-boot:run</p>
<p>La API estará disponible en: http://localhost:8080/api/tasks</p>
<p>⚠️ Solución de problemas: Puerto 8080 ocupado</p>
<p>Si al intentar arrancar la aplicación recibes un error indicando que
el puerto 8080 ya está en uso, tienes dos opciones:</p>
<p>Opción A: Liberar el puerto (Windows)</p>
<p>Abre la terminal (CMD o PowerShell) como administrador.</p>
<p>Ejecuta: netstat -ano | findstr :8080</p>
<p>Identifica el PID (número al final de la línea) y ejecuta: taskkill
/F /PID <número_PID></p>
<p>Opción B: Cambiar el puerto del proyecto Edita el archivo
src/main/resources/application.properties y añade la siguiente línea
para usar otro puerto (ejemplo: 8081):</p>
<p>server.port=8081</p>
<p>📡 Documentación de Endpoints</p>
<ol type="1">
<li>Gestión Principal (CRUD)</li>
</ol>
<p>Método</p>
<p>Endpoint</p>
<p>Descripción</p>
<p>GET</p>
<p>/api/tasks</p>
<p>Obtiene el listado de todas las tareas (10+ tareas de prueba).</p>
<p>POST</p>
<p>/api/tasks</p>
<p>Crea una nueva tarea individual.</p>
<p>GET</p>
<p>/api/tasks/{id}</p>
<p>Obtiene los detalles de una tarea específica por su ID.</p>
<p>PUT</p>
<p>/api/tasks/{id}</p>
<p>Reemplaza una tarea completa por una nueva versión.</p>
<p>DELETE</p>
<p>/api/tasks/{id}</p>
<p>Elimina una tarea de forma permanente (Retorna 204 No Content).</p>
<ol start="2" type="1">
<li>Filtros y Búsquedas</li>
</ol>
<p>Método</p>
<p>Endpoint</p>
<p>Descripción</p>
<p>GET</p>
<p>/api/tasks/priority/{ALTA|MEDIA|BAJA}</p>
<p>Filtra por nivel de prioridad.</p>
<p>GET</p>
<p>/api/tasks/category/{nombre}</p>
<p>Filtra por categoría (ej. Diseño, Desarrollo).</p>
<p>GET</p>
<p>/api/tasks/tag/{etiqueta}</p>
<p>Busca tareas que contengan una etiqueta específica.</p>
<p>GET</p>
<p>/api/tasks/pending</p>
<p>Lista todas las tareas con completed: false.</p>
<ol start="3" type="1">
<li>Acciones de Estado (PATCH)</li>
</ol>
<p>Método</p>
<p>Endpoint</p>
<p>Descripción</p>
<p>PATCH</p>
<p>/api/tasks/{id}/complete</p>
<p>Marca una tarea como completada.</p>
<p>PATCH</p>
<p>/api/tasks/{id}/incomplete</p>
<p>Marca una tarea como pendiente.</p>
<p>📄 Ejemplos de Petición y Respuesta (JSON)</p>
<p>✅ Ejemplo: Crear una Tarea (POST)</p>
<p>Request Body:</p>
<p>{ “title”: “Configurar Seguridad JWT”, “description”: “Implementar
tokens para proteger la API.”, “priority”: “ALTA”, “category”:
“Desarrollo”, “completed”: false, “tags”: [“seguridad”, “backend”] }</p>
<p>Response (201 Created):</p>
<p>{ “id”: “694464c4e85b33c08c3a96af”, “title”: “Configurar Seguridad
JWT”, “completed”: false, “createdAt”: “2025-12-18T20:30:00”,
“updatedAt”: “2025-12-18T20:30:00” }</p>
<p>❌ Ejemplo: Error de Validación (400 Bad Request)</p>
<p>Request: {“title”: ““,”priority”: “BAJA”} Response:</p>
<p>{ “status”: 400, “error”: “Bad Request”, “message”: “Validation
failed”, “errors”: [ { “field”: “title”, “defaultMessage”: “El título es
obligatorio” } ] }</p>
<p>🧪 Pruebas y Población de Datos (Postman)</p>
<p>Se adjunta el archivo de colección de Postman exportado.</p>
<p>Para evaluar el proyecto:</p>
<p>Importar: En Postman, cargar el archivo JSON de la colección.</p>
<p>Población de Datos: En el endpoint POST Crear Tarea, se han guardado
10 Examples con diferentes configuraciones de tareas.</p>
<p>Casos de Error: Se incluyen peticiones para disparar errores 400 y
404 con sus respuestas guardadas para verificación.</p>
