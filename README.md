# CRUD-en-Spring-boot
Creación de un CRUD en Spring Boot.

Se presenta la implementación de un CRUD para la gestión de estudiantes utilizando Spring Boot . 
Se incluyen los endpoints para crear, actualizar, obtener y eliminar estudiantes.

Características Principales
✅ Gestión de Estudiantes
✅ Operaciones CRUD completas
✅ Arquitectura en capas
✅ Patrón DTO para transferencia de datos

Estructura del Proyecto
RegistroUniversitario/
├── src/
│   ├── main/
│   │   ├── java/com/universidad/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── dto/             # Objetos de Transferencia de Datos
│   │   │   ├── model/           # Entidades del dominio
│   │   │   ├── repository/      # Capa de acceso a datos
│   │   │   ├── service/         # Lógica de negocio
│   │   │   └── UniversidadApplication.java
│   │   └── resources/           # Configuraciones
│   └── test/                    # Pruebas
├── .gitignore
├── pom.xml
└── README.md

Endpoints Disponibles

GET	/api/estudiantes -->	Obtener todos los estudiantes
GET	/api/estudiantes/{id} -->	Obtener un estudiante por ID
POST	/api/estudiantes	--> Crear nuevo estudiante
PUT	/api/estudiantes/{id}	--> Actualizar estudiante
DELETE	/api/estudiantes/{id}	--> Eliminar estudiante
