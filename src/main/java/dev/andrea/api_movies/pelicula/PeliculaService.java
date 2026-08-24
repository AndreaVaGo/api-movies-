package dev.andrea.api_movies.pelicula;

import dev.andrea.api_movies.implementations.InterfaceGenericGetService;
import dev.andrea.api_movies.implementations.InterfaceGenericEditService;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTORequest;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;
import java.util.List;

public interface PeliculaService extends
        InterfaceGenericGetService<PeliculaDTOResponse, PeliculaDTORequest>,
        InterfaceGenericEditService<PeliculaDTORequest, PeliculaDTOResponse> {

    PeliculaDTOResponse update(Long id, PeliculaDTORequest dto);
    void delete(Long id);
    List<PeliculaDTOResponse> findByTituloOrGenero(String texto);
}