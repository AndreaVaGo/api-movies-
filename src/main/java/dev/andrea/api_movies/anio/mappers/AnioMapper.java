package dev.andrea.api_movies.anio.mappers;

import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.anio.dtos.AnioDTORequest;
import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;

public class AnioMapper {

    public static AnioEntity toEntity(AnioDTORequest dto) {
        AnioEntity anio = new AnioEntity();
        anio.setAnio(dto.anio());
        return anio;
    }

    public static AnioDTOResponse toDTO(AnioEntity entity) {
        return new AnioDTOResponse(entity.getId(), entity.getAnio());
    }
}