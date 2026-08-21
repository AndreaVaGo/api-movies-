package dev.andrea.api_movies.anio;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnioServiceImpl implements InterfaceGenericService<AnioEntity> {

    private final AnioRepository repository;

    public AnioServiceImpl(AnioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnioEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public AnioEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}