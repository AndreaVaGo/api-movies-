package dev.andrea.api_movies.pelicula;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import java.util.List;

public interface PeliculaService extends InterfaceGenericService<PeliculaEntity> {
    PeliculaEntity add(PeliculaEntity pelicula);
    PeliculaEntity update(Long id, PeliculaEntity pelicula);
    void delete(Long id);
    List<PeliculaEntity> findByTituloOrGenero(String texto);
}