package dev.andrea.api_movies.pelicula.dtos;

import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.reparto.RepartoEntity;
import java.util.List;

public record PeliculaDTOResponse(
    Long id,
    String titulo,
    int calificacion,
    int duracion,
    GeneroEntity genero,
    AnioEntity anio,
    List<RepartoEntity> reparto
) {}