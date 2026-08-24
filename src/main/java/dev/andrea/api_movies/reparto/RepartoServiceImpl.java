package dev.andrea.api_movies.reparto;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepartoServiceImpl implements InterfaceGenericService<RepartoEntity> {

    private final RepartoRepository repository;

    public RepartoServiceImpl(RepartoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepartoEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public RepartoEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RepartoNotFoundException("Reparto not found. Id " + id + " does not exist."));
    }
}