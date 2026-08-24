package dev.andrea.api_movies.implementations;

public interface InterfaceGenericEditService<TRequest, TResponse> {
    TResponse storeEntity(TRequest dto);
}