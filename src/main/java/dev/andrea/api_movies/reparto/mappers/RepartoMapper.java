package dev.andrea.api_movies.reparto.mappers;

import dev.andrea.api_movies.reparto.RepartoEntity;
import dev.andrea.api_movies.reparto.dtos.RepartoDTORequest;
import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;

public class RepartoMapper {

    public static RepartoEntity toEntity(RepartoDTORequest dto) {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setNombre(dto.nombre());
        reparto.setSexo(dto.sexo());
        reparto.setFechaNacimiento(dto.fechaNacimiento());
        return reparto;
    }

    public static RepartoDTOResponse toDTO(RepartoEntity entity) {
        return new RepartoDTOResponse(
            entity.getId(),
            entity.getNombre(),
            entity.getSexo(),
            entity.getFechaNacimiento()
        );
    }
}