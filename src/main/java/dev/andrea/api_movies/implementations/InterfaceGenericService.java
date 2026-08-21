package dev.andrea.api_movies.implementations;

import java.util.List;

public interface InterfaceGenericService<T> {
    List<T> getEntities();
    T getById(Long id);
}