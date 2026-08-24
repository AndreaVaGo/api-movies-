package dev.andrea.api_movies.implementations;

import java.util.List;

public interface InterfaceGenericGetService<TResponse, TRequest> {
    List<TResponse> getEntities();
    TResponse getById(Long id);
}