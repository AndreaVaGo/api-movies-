package dev.andrea.api_movies.pelicula.mappers;

import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.pelicula.PeliculaEntity;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTORequest;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;
import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.reparto.RepartoEntity;
import java.util.List;

public class PeliculaMapper {

    /*toEntity(...) → recibe el DTO (que solo tiene ids) más las entidades reales ya buscadas 
    (GeneroEntity genero, AnioEntity anio, List<RepartoEntity> reparto) — porque el mapper no 
    puede ir a la base de datos por sí mismo a buscar esas entidades con solo los ids; eso lo 
    hará el servicio, que sí tiene acceso a los repositorios, y luego se lo pasa ya "resuelto" al mapper*/
    public static PeliculaEntity toEntity(PeliculaDTORequest dto, GeneroEntity genero, AnioEntity anio, List<RepartoEntity> reparto) {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setTitulo(dto.titulo());
        pelicula.setCalificacion(dto.calificacion());
        pelicula.setDuracion(dto.duracion());
        pelicula.setGenero(genero);
        pelicula.setAnio(anio);
        pelicula.setReparto(reparto);
        return pelicula;
    }

    /*el camino inverso, mucho más simple: coge una entidad ya completa (con sus relaciones ya cargadas) 
    y arma el DTO de respuesta*/
    public static PeliculaDTOResponse toDTO(PeliculaEntity entity) {
        return new PeliculaDTOResponse(
            entity.getId(),
            entity.getTitulo(),
            entity.getCalificacion(),
            entity.getDuracion(),
            entity.getGenero(),
            entity.getAnio(),
            entity.getReparto()
        );
    }
}