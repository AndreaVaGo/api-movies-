package dev.andrea.api_movies.reparto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepartoServiceImpl implements RepartoService {

    private final RepartoRepository repository;

    public RepartoServiceImpl(RepartoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepartoEntity> obtenerTodos() {
        return repository.findAll();
    }
}