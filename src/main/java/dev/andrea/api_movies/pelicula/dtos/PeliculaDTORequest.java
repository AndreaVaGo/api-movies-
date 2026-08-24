package dev.andrea.api_movies.pelicula.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.util.List;

 /*es una forma compacta de Java para clases inmutables que solo guardan datos
(sin necesitar escribir getters/constructor a mano)*/
public record PeliculaDTORequest(
    @NotBlank(message = "El titulo no puede estar vacio")
    String titulo,

    @NotNull(message = "La calificacion no puede ser nula")
    @Min(value = 0, message = "La calificacion no puede ser negativa")
    Integer calificacion,

    @NotNull(message = "La duracion no puede ser nula")
    @Min(value = 1, message = "La duracion debe ser mayor que 0")
    Integer duracion,

    @NotNull(message = "El genero es obligatorio")
    Long generoId,

    @NotNull(message = "El anio es obligatorio")
    Long anioId,

    List<Long> repartoIds
) {}