package dev.andrea.api_movies.reparto;

import dev.andrea.api_movies.implementations.InterfaceGenericGetService;
import dev.andrea.api_movies.implementations.InterfaceGenericEditService;
import dev.andrea.api_movies.reparto.dtos.RepartoDTORequest;
import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;

public interface RepartoService extends
        InterfaceGenericGetService<RepartoDTOResponse, RepartoDTORequest>,
        InterfaceGenericEditService<RepartoDTORequest, RepartoDTOResponse> {
}