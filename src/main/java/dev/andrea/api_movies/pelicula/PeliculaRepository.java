package dev.andrea.api_movies.pelicula;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {
    List<PeliculaEntity> findByTituloContainingIgnoreCase(String titulo);
    List<PeliculaEntity> findByGenero_NombreContainingIgnoreCase(String nombreGenero);
}