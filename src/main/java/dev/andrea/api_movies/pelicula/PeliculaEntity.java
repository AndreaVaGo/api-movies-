package dev.andrea.api_movies.pelicula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.reparto.RepartoEntity;

@Entity
@Table(name = "peliculas")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int calificacion;
    private int duracion;

    @ManyToOne
    private GeneroEntity genero;

    @ManyToOne
    private AnioEntity anio;

    @ManyToMany
    private List<RepartoEntity> reparto;

    public PeliculaEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    public AnioEntity getAnio() {
        return anio;
    }

    public void setAnio(AnioEntity anio) {
        this.anio = anio;
    }

    public List<RepartoEntity> getReparto() {
        return reparto;
    }

    public void setReparto(List<RepartoEntity> reparto) {
        this.reparto = reparto;
    }
}