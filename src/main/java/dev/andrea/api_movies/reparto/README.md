# API REST de Películas

API REST desarrollada con Spring Boot para gestionar una base de datos de películas, con sus géneros, años y reparto (actores/actrices).



## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Web** — para crear los endpoints REST
- **Spring Data JPA** — para el acceso a la base de datos
- **MySQL** — base de datos relacional, levantada automáticamente con Docker Compose
- **H2** — base de datos en memoria, usada automáticamente durante los tests
- **Bean Validation** (`spring-boot-starter-validation`) — para las validaciones de los datos de entrada
- **JUnit 5 + Mockito + Hamcrest** — para los tests
- **Maven** — gestor de dependencias

## Modelado de datos

El proyecto tiene 4 entidades principales:

- **Película** (`peliculas`): título, calificación por edad, duración
- **Género** (`generos`): nombre
- **Año** (`anios`): año de estreno
- **Reparto** (`repartos`): nombre, sexo, fecha de nacimiento

### Relaciones

- **Película → Género**: relación `N:1` (`@ManyToOne`). Muchas películas pueden compartir el mismo género.
- **Película → Año**: relación `N:1` (`@ManyToOne`). Muchas películas pueden ser del mismo año.
- **Película ↔ Reparto**: relación `N:M` (`@ManyToMany`). Una película puede tener varios actores/actrices en su reparto, y una misma persona puede aparecer en varias películas. Esta relación genera automáticamente una tabla intermedia (`peliculas_reparto`).

Los diagramas de Chen y de patas de gallo (crow's foot) del modelado completo se incluyen en la carpeta [`/assets`](./assets) del repositorio:

- [`ER-peliculas-chen.drawio.png`](./assets/ER-peliculas-chen.drawio.png) — diagrama de Chen
- [`diagrama-patasgallo-peliculas.drawio.png`](./assets/diagrama-patasgallo-peliculas.drawio.png) — diagrama de patas de gallo

## Arquitectura del proyecto

El proyecto sigue una arquitectura en capas, organizada por *feature* (una carpeta por cada entidad, en vez de agrupar por tipo de archivo):

```
Controlador → Servicio → Repositorio → Base de datos
```

Cada entidad (`pelicula`, `genero`, `anio`, `reparto`) tiene su propia carpeta con:

- `XEntity.java` — la entidad JPA (representa la tabla)
- `XRepository.java` — interfaz que extiende `JpaRepository`, para hablar con la base de datos
- `XService.java` / `XServiceImpl.java` — la lógica de negocio
- `XController.java` — los endpoints REST
- `dtos/` — los DTOs de entrada (`XDTORequest`) y salida (`XDTOResponse`)
- `mappers/` — la clase que convierte entre DTO y Entity
- `exceptions/` (o directamente en la carpeta principal) — la excepción personalizada `XNotFoundException`

### Interfaces genéricas

Para no repetir código entre las 4 entidades, se usan dos interfaces genéricas (en `implementations/`):

- `InterfaceGenericService<T>` — usada en Género, Año y Reparto para las relaciones simples de consulta (aunque ha sido sustituida por las dos siguientes en la versión final)
- `InterfaceGenericGetService<TResponse, TRequest>` — define `getEntities()` y `getById(id)`
- `InterfaceGenericEditService<TRequest, TResponse>` — define `storeEntity(dto)`

`PeliculaService`, `GeneroService`, `AnioService` y `RepartoService` extienden estas dos interfaces, y `PeliculaService` añade además sus propios métodos (`update`, `delete`, `findByTituloOrGenero`), ya que Película necesita más funcionalidad que el resto.

### Qué hace cada tipo de clase

Para entender el flujo completo de una petición, aquí se explica el papel de cada capa, usando Género como ejemplo:

**Entity (`GeneroEntity.java`)**
Es el molde de una fila de la tabla `generos` en la base de datos. Lleva las anotaciones `@Entity` (le dice a Hibernate que esta clase representa una tabla), `@Table` (el nombre de la tabla), `@Id` y `@GeneratedValue` (marcan el campo `id` como clave primaria autogenerada). Solo tiene atributos, un constructor vacío (necesario para que Hibernate pueda crear instancias) y getters/setters.

**Repository (`GeneroRepository.java`)**
Es una interfaz vacía que extiende `JpaRepository<GeneroEntity, Long>`. Gracias a Spring Data JPA, con solo heredar de `JpaRepository` ya se dispone de métodos como `findAll()`, `findById(id)`, `save(entidad)` o `deleteById(id)`, sin escribir ninguna consulta SQL a mano. En `PeliculaRepository` se añaden además dos métodos derivados (`findByTituloContainingIgnoreCase`, `findByGenero_NombreContainingIgnoreCase`) para el endpoint de búsqueda, cuyo comportamiento lo genera Spring automáticamente a partir del nombre del método.

**Service / ServiceImpl (`GeneroService.java`, `GeneroServiceImpl.java`)**
El `Service` es una interfaz que define qué operaciones ofrece (obtener todas, obtener por id, crear...). El `ServiceImpl` es la clase que las implementa de verdad: recibe el repositorio por constructor (inyección de dependencias) y usa un `Mapper` para convertir entre `Entity` y `DTO`. Aquí es donde vive la lógica de negocio, como la búsqueda de las entidades relacionadas (género, año, reparto) antes de guardar una película.

**Controller (`GeneroController.java`)**
Es la puerta de entrada HTTP: recibe las peticiones (`GET`, `POST`...) y delega el trabajo al `Service`. Usa anotaciones como `@GetMapping`, `@PostMapping`, `@PathVariable` (para capturar valores de la URL) y `@RequestBody` (para leer el JSON enviado por el cliente). El controlador nunca contiene lógica de negocio ni habla directamente con el repositorio.

**DTOs (`dtos/GeneroDTORequest.java`, `dtos/GeneroDTOResponse.java`)**
Records de Java que definen qué datos entran y salen de la API, desacoplando el contrato de la API del modelo de persistencia. El `Request` lleva las anotaciones de validación (`@NotBlank`, `@NotNull`...).

**Mapper (`mappers/GeneroMapper.java`)**
Clase con dos métodos estáticos, `toEntity` (DTO → Entity, para guardar) y `toDTO` (Entity → DTO, para responder al cliente).

**Excepción (`GeneroNotFoundException.java`)**
Hereda de `ApiException` (que a su vez hereda de `RuntimeException`), y lleva `@ResponseStatus(HttpStatus.NOT_FOUND)` para que, en cuanto se lance, Spring sepa devolver un 404. El `GlobalExceptionHandler` la captura de forma centralizada.

**Tests (`GeneroEntityTest.java`, `GeneroServiceImplTest.java`, `GeneroControllerTest.java`)**
Cada entidad tiene tres archivos de test, siguiendo siempre el mismo patrón:
- `EntityTest`: comprueba que la entidad se puede crear y que sus setters/getters funcionan.
- `ServiceImplTest`: usa Mockito (`@Mock`, `@ExtendWith(MockitoExtension.class)`) para simular el repositorio, de forma que se puede probar la lógica del servicio sin necesitar una base de datos real.
- `ControllerTest`: usa `@WebMvcTest` y `MockMvc` para simular peticiones HTTP reales, mockeando el servicio con `@MockitoBean`, sin levantar el servidor completo.

### DTOs (Data Transfer Objects)

Los controladores nunca exponen las entidades JPA directamente al cliente. En su lugar, usan:

- **`XDTORequest`**: lo que el cliente envía (por ejemplo, al crear o actualizar). No incluye el `id` (se genera solo). En el caso de Película, las relaciones se envían como simples IDs (`generoId`, `anioId`, `repartoIds`), no como objetos completos.
- **`XDTOResponse`**: lo que la API devuelve al cliente.

Una clase `XMapper` (con métodos estáticos `toEntity` y `toDTO`) se encarga de convertir entre ambos formatos.

### Validaciones

Los DTOs de entrada (`XDTORequest`) usan anotaciones de Bean Validation para comprobar que los datos son correctos antes de que lleguen a la lógica de negocio:

- `@NotBlank` — el campo no puede estar vacío ni ser nulo (para textos)
- `@NotNull` — el campo no puede ser nulo (para números y otros tipos)
- `@Min` — el valor numérico debe ser mayor o igual que el indicado

Estas validaciones se activan en los controladores añadiendo `@Valid` delante del `@RequestBody`.

### Manejo de errores

Todas las excepciones personalizadas (`PeliculaNotFoundException`, `GeneroNotFoundException`, `AnioNotFoundException`, `RepartoNotFoundException`) heredan de una excepción base común, `ApiException` (que a su vez hereda de `RuntimeException`).

Una clase `GlobalExceptionHandler`, anotada con `@RestControllerAdvice`, captura estas excepciones de forma centralizada y construye una respuesta JSON con el código de estado correcto (por ejemplo, `404 Not Found`), un mensaje descriptivo y la fecha/hora del error.

También se captura `MethodArgumentNotValidException` (la excepción que lanza Spring cuando falla una validación `@Valid`), devolviendo un `400 Bad Request` con el campo concreto que falló y su mensaje de error.

## Instalación y puesta en marcha

### Requisitos previos

- **Java 21**
- **Maven** (o usar el wrapper incluido, `mvnw`)
- **Docker Desktop** (para levantar la base de datos MySQL automáticamente)

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/AndreaVaGo/api-movies-.git
   cd api-movies-
   ```
   Repositorio: [https://github.com/AndreaVaGo/api-movies-](https://github.com/AndreaVaGo/api-movies-)

2. Asegúrate de que **Docker Desktop está abierto y corriendo**. Spring Boot levantará automáticamente un contenedor con MySQL al arrancar la aplicación (gracias a la dependencia Docker Compose Support), usando la configuración de `compose.yaml`.

3. Arranca el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```
   (en Windows con Git Bash) o
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
   (en Windows con cmd/PowerShell)

4. La aplicación arrancará en `http://localhost:8080`, con la base de datos ya rellenada con datos de prueba (definidos en `src/main/resources/data.sql`).

### Ejecutar los tests

```bash
./mvnw test
```

Los tests usan una base de datos H2 en memoria, por lo que no requieren tener Docker/MySQL corriendo.

## Documentación de los endpoints

Todas las rutas empiezan por el prefijo `/api/v1`.

### Película

| Método | Ruta | Descripción | Body (ejemplo) |
|---|---|---|---|
| GET | `/api/v1/peliculas` | Obtiene todas las películas | — |
| GET | `/api/v1/peliculas/{id}` | Obtiene una película por su ID | — |
| POST | `/api/v1/peliculas` | Crea una nueva película | `{"titulo": "Matrix", "calificacion": 16, "duracion": 136, "generoId": 1, "anioId": 1, "repartoIds": [1, 2]}` |
| PUT | `/api/v1/peliculas/{id}` | Actualiza una película existente | Igual que el POST |
| DELETE | `/api/v1/peliculas/{id}` | Elimina una película | — |
| GET | `/api/v1/peliculas/buscar?texto=` | Busca películas por título o género (endpoint extra `findBy`) | — |

### Género

| Método | Ruta | Descripción | Body (ejemplo) |
|---|---|---|---|
| GET | `/api/v1/generos` | Obtiene todos los géneros | — |
| GET | `/api/v1/generos/{id}` | Obtiene un género por su ID | — |
| POST | `/api/v1/generos` | Crea un nuevo género | `{"nombre": "Comedia"}` |

### Año

| Método | Ruta | Descripción | Body (ejemplo) |
|---|---|---|---|
| GET | `/api/v1/anios` | Obtiene todos los años | — |
| GET | `/api/v1/anios/{id}` | Obtiene un año por su ID | — |
| POST | `/api/v1/anios` | Crea un nuevo año | `{"anio": 2024}` |

### Reparto

| Método | Ruta | Descripción | Body (ejemplo) |
|---|---|---|---|
| GET | `/api/v1/repartos` | Obtiene todo el reparto | — |
| GET | `/api/v1/repartos/{id}` | Obtiene una persona del reparto por su ID | — |
| POST | `/api/v1/repartos` | Crea una nueva persona en el reparto | `{"nombre": "Marta Sanchez", "sexo": "Mujer", "fechaNacimiento": "1992-11-04"}` |

### Ejemplo de respuesta de error (404)

```json
{
  "timestamp": "2026-08-25T13:00:00.123",
  "status": 404,
  "error": "Not Found",
  "message": "Pelicula not found. Id 999 does not exist."
}
```

### Ejemplo de respuesta de error de validación (400)

```json
{
  "titulo": "El titulo no puede estar vacio"
}
```

## Autor

Andrea 