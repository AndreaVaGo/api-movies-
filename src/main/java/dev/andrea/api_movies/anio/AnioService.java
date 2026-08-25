package dev.andrea.api_movies.anio;

import dev.andrea.api_movies.implementations.InterfaceGenericGetService;
import dev.andrea.api_movies.implementations.InterfaceGenericEditService;
import dev.andrea.api_movies.anio.dtos.AnioDTORequest;
import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;

public interface AnioService extends
        InterfaceGenericGetService<AnioDTOResponse, AnioDTORequest>,
        InterfaceGenericEditService<AnioDTORequest, AnioDTOResponse> {
}