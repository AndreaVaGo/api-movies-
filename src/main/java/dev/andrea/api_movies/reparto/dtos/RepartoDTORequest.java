package dev.andrea.api_movies.reparto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record RepartoDTORequest(
    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre,

    @NotBlank(message = "El sexo no puede estar vacio")
    String sexo,

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    LocalDate fechaNacimiento
) {}