# Clase 2 — Modernizacion de Aplicaciones Java Legacy a Spring Boot

**Universidad Tecnologica Nacional — Buenos Aires**
Capacitacion: Migracion Java 7 a Java 21

---

## Objetivo Pedagogico

Esta practica guia a los alumnos a traves del proceso de modernizacion de una aplicacion web Java
legacy (Servlets + JSP) hacia una API REST con Spring Boot 3, sin reescribir el codigo de acceso
a datos existente.

Al finalizar la practica, el alumno deberia:

- Comprender los principios de **Inversion de Dependencias** y **Constructor Injection**.
- Aplicar el **patron Adapter** para conectar codigo viejo con interfaces nuevas.
- Conocer la estructura en capas de una aplicacion Spring Boot moderna.
- Ejecutar tests unitarios sin contexto de Spring.
- Consumir una API REST y verificar contratos HTTP.

---

## Estructura del Proyecto

```
practica-clase-2-utn/
|-- pom.xml
|-- README.md
|-- .gitignore
`-- src/
    |-- main/
    |   |-- java/ar/edu/utn/clientes/
    |   |   |-- ClientesApplication.java          <- Punto de entrada Spring Boot
    |   |   |
    |   |   |-- model/
    |   |   |   `-- Cliente.java                  <- Modelo de dominio legacy
    |   |   |
    |   |   |-- dao/                              <- CAPA LEGACY (no tocar)
    |   |   |   |-- ClienteDao.java               <- Interfaz DAO original
    |   |   |   `-- ClienteDaoMemoria.java        <- Implementacion en memoria
    |   |   |
    |   |   |-- servlet/                          <- CAPA LEGACY (no tocar)
    |   |   |   `-- BuscarClienteServlet.java     <- Servlet original
    |   |   |
    |   |   `-- api/                              <- CAPA MODERNA (se desarrolla en la practica)
    |   |       |-- dto/
    |   |       |   `-- ClienteDto.java           <- DTO para respuesta JSON
    |   |       |-- repository/
    |   |       |   |-- ClienteRepository.java    <- Interfaz moderna (Optional)
    |   |       |   `-- ClienteRepositoryLegacyAdapter.java  <- Adapter pattern
    |   |       |-- service/
    |   |       |   `-- ClienteService.java       <- Logica de negocio
    |   |       |-- controller/
    |   |       |   `-- ClienteController.java    <- REST Controller
    |   |       `-- config/
    |   |           `-- AppConfig.java            <- Configuracion de Beans
    |   `-- resources/
    |       `-- application.properties
    `-- test/
        `-- java/ar/edu/utn/clientes/
            |-- dao/
            |   `-- ClienteDaoMemoriaTest.java
            `-- api/service/
                `-- ClienteServiceTest.java
```

### Dos capas bien diferenciadas

| Capa | Paquete | Descripcion |
|------|---------|-------------|
| **Legacy** | `clientes.model`, `clientes.dao`, `clientes.servlet` | Codigo original. NO se modifica. |
| **Moderna** | `clientes.api.*` | Codigo nuevo. Se desarrolla durante la practica. |

---

## Secuencia de la Practica (55 minutos)

### Fase 1 — Contexto (5 min)
- Revisar el codigo legacy: `ClienteDao`, `ClienteDaoMemoria`, `BuscarClienteServlet`.
- Identificar los problemas: dependencias hardcodeadas, null returns, mezcla HTTP + logica.

### Fase 2 — Compilar y ejecutar tests base (3 min)
```bash
mvn clean compile
mvn test
```
Todos los tests deben pasar antes de tocar nada.

### Fase 3 — Analizar el modelo de dominio (3 min)
- Leer `Cliente.java`: clase inmutable, sin Lombok, sin frameworks.
- Discutir por que los campos son `final`.

### Fase 4 — Comprender el patron Adapter (7 min)
- Leer `ClienteRepository` (interfaz moderna con `Optional`).
- Leer `ClienteRepositoryLegacyAdapter`: como envuelve el DAO legacy.
- Discutir: ¿por que `Optional.ofNullable` y no un `if (cliente != null)`?

### Fase 5 — Analizar el Servicio (7 min)
- Leer `ClienteService`: no importa nada de HTTP.
- Identificar `ClienteNoEncontradoException` como inner class estatica.
- Discutir: ¿por que la excepcion esta dentro del servicio y no fuera?

### Fase 6 — Analizar el Controller (5 min)
- Leer `ClienteController`: solo anotaciones HTTP, delega todo al servicio.
- Discutir: ¿que pasaria si el try/catch estuviera en el servicio?

### Fase 7 — Analizar la configuracion de Spring (5 min)
- Leer `AppConfig`: los tres `@Bean` encadenan las dependencias.
- Discutir: ¿que ventaja tiene esto vs `@Autowired` con `@Component`?

### Fase 8 — Levantar la aplicacion (5 min)
```bash
mvn spring-boot:run
```
Verificar el log de Spring Boot. Identificar el puerto 8080.

### Fase 9 — Consumir la API (7 min)
```bash
# Cliente existente
curl -i http://localhost:8080/api/clientes/1

# Cliente inexistente
curl -i http://localhost:8080/api/clientes/999

# Otro cliente
curl -i http://localhost:8080/api/clientes/42
```

Discutir las respuestas HTTP: codigo de estado, headers, body JSON.

### Fase 10 — Ejercicio guiado: agregar campo (8 min)
Agregar el campo `telefono` al flujo completo:
1. Agregar `telefono` a `Cliente.java` y `ClienteDaoMemoria.java`.
2. Agregar `telefono` a `ClienteDto.java`.
3. Actualizar `ClienteService.obtenerCliente()` para mapear el campo.
4. Verificar con `curl`.

### Fase 11 — Cierre y preguntas (5 min)
- ¿Donde esta el test de integracion del controller?
- ¿Como se testea el controller con `@WebMvcTest`?
- ¿Que pasaria si hubiera dos implementaciones de `ClienteRepository`?

---

## Checklist de Validacion

Antes de dar por finalizada la practica, verificar:

- [ ] `mvn clean compile` → **BUILD SUCCESS**
- [ ] `mvn test` → todos los tests en verde
- [ ] `mvn spring-boot:run` → arranca sin errores en el log
- [ ] `curl http://localhost:8080/api/clientes/1` → HTTP 200 + JSON con Ana
- [ ] `curl http://localhost:8080/api/clientes/2` → HTTP 200 + JSON con Bruno
- [ ] `curl http://localhost:8080/api/clientes/42` → HTTP 200 + JSON con Carlos
- [ ] `curl http://localhost:8080/api/clientes/999` → HTTP 404
- [ ] El codigo legacy (dao/, servlet/) no fue modificado
- [ ] No hay `@Autowired` fuera de `AppConfig`
- [ ] No hay Lombok en el proyecto

---

## Comandos de Referencia

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Levantar servidor de desarrollo
mvn spring-boot:run

# Empaquetar
mvn clean package

# Git inicial
git init
git add .
git commit -m "chore: estructura inicial del proyecto"
```

---

## Proximos Pasos

Una vez dominada esta practica, los siguientes temas recomendados son:

1. **Tests de integracion con `@SpringBootTest`** — levantar el contexto completo en un test.
2. **`@WebMvcTest`** — testear el controller de forma aislada con MockMvc.
3. **Spring Data JPA** — reemplazar `ClienteDaoMemoria` por una implementacion con base de datos real.
4. **Manejo de errores global** — `@ControllerAdvice` para centralizar los handlers de excepciones.
5. **Validacion de entrada** — Bean Validation con `@Valid` y `@NotNull`.
6. **Documentacion de la API** — Springdoc OpenAPI / Swagger UI.
7. **Docker** — contenerizar la aplicacion con un `Dockerfile` multi-stage.

---

## Datos de Prueba en Memoria

| ID | Nombre | Email |
|----|--------|-------|
| 1  | Ana    | ana@example.com    |
| 2  | Bruno  | bruno@example.com  |
| 42 | Carlos | carlos@example.com |

---

*Practica desarrollada para el curso de Capacitacion "Migracion Java 7 a Java 21" — UTN BA.*
