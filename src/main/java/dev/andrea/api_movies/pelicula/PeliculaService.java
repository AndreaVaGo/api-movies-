package dev.andrea.api_movies.pelicula;

import java.util.List;

public interface PeliculaService {
    List<PeliculaEntity> obtenerTodas();
    PeliculaEntity obtenerPorId(Long id);
    PeliculaEntity crear(PeliculaEntity pelicula);
    PeliculaEntity actualizar(Long id, PeliculaEntity pelicula);
    void eliminar(Long id);
    List<PeliculaEntity> buscarPorTituloOGenero(String texto);
}