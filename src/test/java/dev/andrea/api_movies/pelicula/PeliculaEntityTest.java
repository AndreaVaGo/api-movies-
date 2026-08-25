package dev.andrea.api_movies.pelicula;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class PeliculaEntityTest {

    @Test
    void testPeliculaEntity_Initialization() {
        PeliculaEntity pelicula = new PeliculaEntity();
        assertThat(pelicula, is(instanceOf(PeliculaEntity.class)));
    }

    @Test
    void testPeliculaEntity_SettersAndGetters() {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setTitulo("Matrix");
        pelicula.setCalificacion(16);
        pelicula.setDuracion(136);

        assertThat(pelicula.getTitulo(), is(equalTo("Matrix")));
        assertThat(pelicula.getCalificacion(), is(equalTo(16)));
        assertThat(pelicula.getDuracion(), is(equalTo(136)));
    }
}