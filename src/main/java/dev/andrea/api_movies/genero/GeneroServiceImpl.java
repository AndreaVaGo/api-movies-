package dev.andrea.api_movies.genero;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GeneroEntity> obtenerTodos() {
        return repository.findAll();
    }
}