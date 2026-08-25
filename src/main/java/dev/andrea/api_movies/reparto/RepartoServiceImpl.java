package dev.andrea.api_movies.reparto;

import dev.andrea.api_movies.reparto.dtos.RepartoDTORequest;
import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;
import dev.andrea.api_movies.reparto.mappers.RepartoMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepartoServiceImpl implements RepartoService {

    private final RepartoRepository repository;

    public RepartoServiceImpl(RepartoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepartoDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(RepartoMapper::toDTO)
                .toList();
    }

    @Override
    public RepartoDTOResponse getById(Long id) {
        RepartoEntity reparto = repository.findById(id)
                .orElseThrow(() -> new RepartoNotFoundException("Reparto not found. Id " + id + " does not exist."));
        return RepartoMapper.toDTO(reparto);
    }

    @Override
    public RepartoDTOResponse storeEntity(RepartoDTORequest dto) {
        RepartoEntity reparto = RepartoMapper.toEntity(dto);
        RepartoEntity repartoGuardado = repository.save(reparto);
        return RepartoMapper.toDTO(repartoGuardado);
    }
}