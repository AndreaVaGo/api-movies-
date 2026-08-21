package dev.andrea.api_movies.anio;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnioServiceImpl implements AnioService {

    private final AnioRepository repository;

    public AnioServiceImpl(AnioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnioEntity> obtenerTodos() {
        return repository.findAll();
    }
}