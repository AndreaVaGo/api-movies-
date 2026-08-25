package dev.andrea.api_movies.genero.dtos;

import jakarta.validation.constraints.NotBlank;

public record GeneroDTORequest(
    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre
) {}