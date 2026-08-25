package dev.andrea.api_movies.anio.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record AnioDTORequest(
    @NotNull(message = "El anio no puede ser nulo")
    @Min(value = 1900, message = "El anio debe ser mayor que 1900")
    Integer anio
) {}