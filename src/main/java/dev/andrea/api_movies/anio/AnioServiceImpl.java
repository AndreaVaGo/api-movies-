package dev.andrea.api_movies.anio;

import dev.andrea.api_movies.anio.dtos.AnioDTORequest;
import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;
import dev.andrea.api_movies.anio.mappers.AnioMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import dev.andrea.api_movies.anio.exceptions.AnioNotFoundException;

@Service
public class AnioServiceImpl implements AnioService {

    private final AnioRepository repository;

    public AnioServiceImpl(AnioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnioDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(AnioMapper::toDTO)
                .toList();
    }

    @Override
    public AnioDTOResponse getById(Long id) {
        AnioEntity anio = repository.findById(id)
                .orElseThrow(() -> new AnioNotFoundException("Anio not found. Id " + id + " does not exist."));
        return AnioMapper.toDTO(anio);
    }

    @Override
    public AnioDTOResponse storeEntity(AnioDTORequest dto) {
        AnioEntity anio = AnioMapper.toEntity(dto);
        AnioEntity anioGuardado = repository.save(anio);
        return AnioMapper.toDTO(anioGuardado);
    }
}