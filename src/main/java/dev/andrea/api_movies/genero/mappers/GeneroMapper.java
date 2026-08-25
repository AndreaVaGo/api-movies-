package dev.andrea.api_movies.genero.mappers;

import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.genero.dtos.GeneroDTORequest;
import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;

public class GeneroMapper {

    public static GeneroEntity toEntity(GeneroDTORequest dto) {
        GeneroEntity genero = new GeneroEntity();
        genero.setNombre(dto.nombre());
        return genero;
    }

    public static GeneroDTOResponse toDTO(GeneroEntity entity) {
        return new GeneroDTOResponse(entity.getId(), entity.getNombre());
    }
}