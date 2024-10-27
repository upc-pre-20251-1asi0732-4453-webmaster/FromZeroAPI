<br />

<br />
<div align="center">
  <a href="https://github.com/JosephLlacchua/fromZero-backend" target="_blank">
    <img src="src/main/resources/assets/Fromzero.png" alt="Logo" width="auto" height="100">
  </a>
  <h3 align="center">From Zero App Backend API</h3>
  <p align="center">
From Zero Backend Application, built using Spring Boot, Spring Data JPA, with MySQL persistance.
    <br />
    <a href=""><strong>Explore the project documentation »</strong></a>
    <br />
    <br />
    <a href="" target="_blank">View Demo</a>
    ·
    <a href="https://github.com/JosephLlacchua/fromZero-backend/issues" target="_blank">Report Bug</a>
    ·
    <a href="https://github.com/JosephLlacchua/fromZero-backend/issues" target="_blank">Request Feature</a>
  </p>
</div>

##  Sobre el Proyecto FromZeroAPI`
FromZeroAPI es un backend escalable y robusto diseñado específicamente para aplicaciones móviles. Facilita la interacción entre empresas y freelancers, brindando un espacio eficiente para que las empresas publiquen proyectos y los desarrolladores freelance puedan aplicar.

## Características

- **Autenticación y Autorización**: Implementa Spring Security con JWT para proteger las rutas.
- **Roles de Usuario**: Los usuarios tienen roles diferenciados, como "ROLE_DEVELOPER" y "ROLE_ENTERPRISE".
- **Publicación y Aplicación a Ofertas**: Las empresas pueden publicar trabajos y los freelancers pueden aplicar a los
  mismos.
- **API REST**: Se proveen endpoints para realizar operaciones CRUD sobre usuarios, trabajos y aplicaciones.
- **Despliegue con Docker**: La aplicación está contenida en una imagen Docker para facilitar el despliegue.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security** (con JWT)
- **Maven**
- **MySQL**
- **Docker**
- **Spring Data JPA**
- **Spring Boot Starter Web**
- **Spring Boot Starter Validation**
- **Spring Boot DevTools**
- **SpringDoc OpenAPI** (para documentación de la API)

### Funcionalidades Destacadas

### Autenticación y Autorización con JWT

La aplicación incluye un sistema de autenticación basado en JWT. Los usuarios deben registrarse o iniciar sesión para
acceder a las funcionalidades protegidas.

- **Login y Registro**: Autenticación para empresas y freelancers.
- **Verificación JWT**: Autenticación segura utilizando JSON Web Tokens para la protección de rutas.

<div align="center">
  <img src="https://i.imgur.com/wGig8Bq.png" alt="Controlador de Autenticación" style="max-width: 90%; height: auto;">
</div>

### Gestión de Proyectos

La aplicación permite a las empresas y freelancers gestionar proyectos de manera eficiente.

- **Creación de Proyectos**: Las empresas pueden crear nuevos proyectos especificando detalles como nombre, descripción,
  lenguajes de programación y frameworks.
- **Asignación de Desarrolladores**: Las empresas pueden asignar desarrolladores a proyectos específicos.
- **Seguimiento de Proyectos**: Los usuarios pueden ver el estado de los proyectos y obtener detalles sobre los mismos.
- **Aplicación a Proyectos**: Los freelancers pueden buscar y aplicar a proyectos disponibles.

<div align="center">
  <img src="https://i.imgur.com/7Vdozzj.png" alt="Controlador de Autenticación" style="max-width: 90%; height: auto;">
</div>

### Gestión de Perfiles

La aplicación permite a los usuarios gestionar sus perfiles de manera eficiente.

- **Edición de Perfil**: Los usuarios pueden actualizar su información personal y profesional.
- **Visualización de Perfil**: Los usuarios pueden ver los perfiles de otros usuarios, incluyendo sus habilidades y
  experiencia.
- **Configuración de Preferencias**: Los usuarios pueden configurar sus preferencias de notificación y privacidad.

<div align="center">
  <img src="https://i.imgur.com/wGig8Bq.png" alt="Controlador de Autenticación" style="max-width: 90%; height: auto;">
</div>

### Gestión de Mensajes

La aplicación incluye un sistema de mensajería que permite a los usuarios comunicarse entre sí.

- **Envío de Mensajes**: Los usuarios pueden enviar mensajes a otros usuarios.
- **Recepción de Mensajes**: Los usuarios pueden recibir mensajes de otros usuarios.
- **Listado de Mensajes**: Los usuarios pueden ver una lista de todos los mensajes recibidos.

<div align="center">
  <img src="https://i.imgur.com/5OuY2ay.png" alt="Controlador de Autenticación" style="max-width: 90%; height: auto;">
</div>

### Soporte

La aplicación incluye un sistema de soporte para ayudar a los usuarios con cualquier problema o pregunta.

- **Sistema de Tickets**: Los usuarios pueden enviar tickets de soporte para recibir ayuda personalizada.
- **Seguimiento de Tickets**: Los usuarios pueden ver el estado de sus tickets y recibir actualizaciones sobre los
  mismos.

## Configuración de la Base de Datos

La aplicación está configurada para usar MySQL como base de datos. Asegúrate de tener un servidor MySQL en ejecución.
Edita el archivo `application.properties` con la información de tu base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Documentación de Swagger
- **Swagger UI**: Una interfaz gráfica interactiva para probar los endpoints de la API.
- **API Docs**: Documentación en formato JSON que describe todos los endpoints y sus parámetros.

Accede a la documentación de Swagger en desplegado :

- [FromzeroApi](https://github-app-fromzero-latest.onrender.com/swagger-ui/index.html#/)


<div align="center">
<a href="https://github-app-fromzero-latest.onrender.com/swagger-ui/index.html#/">
    <img src="https://i.imgur.com/WlfZayd.png" alt="Support System" style="max-width: 90%; height: auto;">
   </a>
</div>

La API de FromZeroAPI sigue un diseño RESTful, facilitando la gestión de usuarios, ofertas de trabajo, y proyectos. A
continuación, te mostramos un resumen de los principales controladores y sus respectivos endpoints


### Controlador de Autenticación

Este controlador gestiona el registro, login y autenticación de usuarios.

| Método | Endpoint                                  | Descripción                                   | Autorización |
|--------|-------------------------------------------|-----------------------------------------------|--------------|
| POST   | /api/v1/authentication/sign-up/enterprise | Registro de un nuevo usuario (empresa).       | Público      |
| POST   | /api/v1/authentication/sign-up/developer  | Registro de un nuevo usuario (desarrollador). | Público      |
| POST   | /api/v1/authentication/sign-in            | Inicio de sesión con credenciales de usuario. | Público      |



### Controlador de Perfiles

Este controlador gestiona la información y actualizaciones de perfiles tanto de empresas como de desarrolladores en la plataforma.

#### Empresas

| Método | Endpoint                                | Descripción                              | Autorización                    |
|--------|-----------------------------------------|------------------------------------------|---------------------------------|
| GET    | /api/v1/enterprises/{enterpriseId}      | Obtiene los datos de una empresa por su ID | ROLE_ENTERPRISE, ROLE_ADMIN |
| PUT    | /api/v1/enterprises/{enterpriseId}      | Actualiza los datos de una empresa por su ID | ROLE_ENTERPRISE                     |
| GET    | /api/v1/enterprises                     | Obtiene todas las empresas               | ROLE_ENTERPRISE, ROLE_DEVELOPER                 |

#### Desarrolladores

| Método | Endpoint                                | Descripción                              | Autorización          |
|--------|-----------------------------------------|------------------------------------------|-----------------------|
| GET    | /api/v1/developers/{developerId}        | Obtiene los datos de un desarrollador por su ID | ROLE_DEVELOPER, ROLE_ADMIN |
| PUT    | /api/v1/developers/{developerId}        | Actualiza los datos de un desarrollador por su ID | ROLE_ADMIN            |
| GET    | /api/v1/developers                      | Obtiene todos los desarrolladores        | ROLE_DEVELOPER             |
### Controlador de Proyectos

Este controlador gestiona la creación y seguimiento de proyectos en la plataforma.

| Método | Endpoint                                      | Descripción                                    | Autorización          |
|--------|-----------------------------------------------|------------------------------------------------|-----------------------|
| GET    | /api/v1/projects                              | Obtiene todos los proyectos                    | ROLE_ENTERPRISE, ROLE_ADMIN |
| POST   | /api/v1/projects                              | Crea un nuevo proyecto                         | ROLE_ENTERPRISE            |
| PATCH  | /api/v1/projects/{projectId}/assign-developer | Asigna un desarrollador a un proyecto          | ROLE_ENTERPRISE            |
| PATCH  | /api/v1/projects/{projectId}/add-candidate    | Agrega un candidato a un proyecto              | ROLE_ENTERPRISE            |
| GET    | /api/v1/projects/{id}                         | Obtiene un proyecto por su ID                  | ROLE_ENTERPRISE, ROLE_ADMIN |
| GET    | /api/v1/projects/enterprise/{enterpriseUserId}| Obtiene todos los proyectos por ID de empresa  | ROLE_ENTERPRISE, ROLE_ADMIN |
| GET    | /api/v1/projects/developer/{developerUserId}  | Obtiene todos los proyectos por ID de desarrollador | ROLE_ENTERPRISE        |
| GET    | /api/v1/projects/by-state                     | Obtiene todos los proyectos por estado         | ROLE_ENTERPRISE, ROLE_ADMIN |
### Controlador de Mensajes

El controlador de mensajes permite a los usuarios comunicarse dentro de la plataforma.

| Método | Endpoint                 | Descripción                                  | Autorización |
|--------|--------------------------|----------------------------------------------|--------------|
| POST   | /messages                | Envía un nuevo mensaje a otro usuario.       | ROLE_ENTERPRISE, ROLE_DEVELOPER   |
| GET    | /messages/recipient/{id} | Lista los mensajes recibidos por un usuario. | ROLE_ENTERPRISE, ROLE_DEVELOPER   |



## Despliegue

### Despliegue Local

1. Clona el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/FromZeroAPI.git
    ```
2. Navega al directorio del proyecto:
   ```sh
   cd FromZeroAPI
    ```
3. Construye la imagen Docker:
   ```sh
   docker build -t fromzeroapi .
    ```
4. Ejecuta el contenedor Docker:
   ```sh
   docker run -p 8080:8080 fromzeroapi
    ```

### Despliegue en Render usando Docker Hub

#### Pasos para el despliegue:

1. **Subir tu imagen a Docker Hub**

   Asegúrate de que tu imagen esté correctamente subida a Docker Hub. Puedes verificar esto navegando a tu repositorio
   en Docker Hub, por ejemplo:

   [https://hub.docker.com/r/tu-usuario/fromzeroapi](https://hub.docker.com/r/tu-usuario/fromzeroapi)

   Si aún no has subido la imagen, puedes hacerlo con los siguientes comandos:

   ```bash
   # Inicia sesión en Docker Hub
   docker login

   # Construye la imagen si no lo has hecho
   docker build -t tu-usuario/fromzeroapi .

   # Etiqueta la imagen
   docker tag fromzeroapi tu-usuario/fromzeroapi:latest

   # Sube la imagen a Docker Hub
   docker push tu-usuario/fromzeroapi:latest
    ```

<p align="center">
  <img src="https://i.imgur.com/OEu4cMq.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>
<p align="center">Vista previa de la imagen subida a Docker Hub</p>

2. **Crear un servicio en Render**

   Dirígete al sitio de Render y sigue estos pasos:

    - Inicia sesión o regístrate.
    - Crea un nuevo servicio Web y selecciona el repositorio donde se encuentra tu proyecto.
    - Asegúrate de seleccionar Docker como la opción de despliegue.
    - Render detectará automáticamente tu archivo Dockerfile.

<p align="center">
  <img src="https://i.imgur.com/gHABEhy.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>

<p align="center">
  <img src="https://i.imgur.com/3mBuwaP.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>
<p align="center">Vista previa de la creacion del servicio</p>

3. **Configurar las variables de entorno**

   Render te permitirá agregar variables de entorno necesarias para la ejecución de la aplicación, como las credenciales
   de tu base de datos o el puerto de la aplicación. Asegúrate de configurar las siguientes variables:

    - `DB_PASSWORD`: La contraseña de tu base de datos.
    - `DB_URL`: La URL de conexión a tu base de datos.
    - `DB_USER_NAME`: El nombre de usuario de tu base de datos.

<p align="center">
  <img src="https://i.imgur.com/KLbCCQA.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>
<p align="center">Vista previa configuraciones  variables de entorno</p>

4. **Construir y desplegar la imagen**

Render automáticamente construirá la imagen Docker utilizando el Dockerfile y desplegará la aplicación en su
infraestructura. Dependiendo de la configuración, tu aplicación debería estar accesible en un dominio proporcionado por
Render.
<p align="center">
  <img src="https://i.imgur.com/ZklNe6k.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>

<p align="center">
  <img src="https://i.imgur.com/BTKEC6e.png" alt="Despliegue en Render" style="max-width: 75%; height: auto;">
</p>
<p align="center">Vista previa del despliegue en Render</p>

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas contribuir, por favor sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una rama con tu nueva funcionalidad
   ```sh
   git checkout -b feature/nueva-funcionalidad
    ```
3. Realiza tus cambios y haz commit
   ```sh
   git commit -am 'Añadir nueva funcionalidad'
    ```
4. Empuja tu rama
   ```sh
   git push origin feature/nueva-funcionalidad
    ```
5. Abre un Pull Request.

# License

Distributed under the MIT License. See `LICENSE.txt` for more information.

# FromZeroAPI

This project was generated with [Spring Boot Framework](https://spring.io/)
3.1.0.<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

[![Spring][spring-shield]][spring-url]<br>
[![Docker][docker-shield]][docker-url]<br>
<br>
<!-- MARKDOWN LINKS & IMAGES -->

[spring-url]: https://spring.io/

[spring-shield]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[docker-url]: https://www.docker.com/

[docker-shield]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white


