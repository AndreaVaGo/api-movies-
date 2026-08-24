package dev.andrea.api_movies.genero;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneroServiceImpl implements InterfaceGenericService<GeneroEntity> {

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GeneroEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public GeneroEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GeneroNotFoundException("Genero not found. Id " + id + " does not exist."));
    }
}