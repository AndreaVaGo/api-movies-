package dev.andrea.api_movies.genero;

import dev.andrea.api_movies.implementations.InterfaceGenericGetService;
import dev.andrea.api_movies.implementations.InterfaceGenericEditService;
import dev.andrea.api_movies.genero.dtos.GeneroDTORequest;
import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;

public interface GeneroService extends
        InterfaceGenericGetService<GeneroDTOResponse, GeneroDTORequest>,
        InterfaceGenericEditService<GeneroDTORequest, GeneroDTOResponse> {
}