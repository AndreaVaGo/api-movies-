package dev.andrea.api_movies.reparto.dtos;

import java.time.LocalDate;

public record RepartoDTOResponse(
    Long id,
    String nombre,
    String sexo,
    LocalDate fechaNacimiento
) {}