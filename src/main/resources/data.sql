DELETE FROM peliculas_reparto;
DELETE FROM peliculas;
DELETE FROM generos;
DELETE FROM anios;
DELETE FROM repartos;

INSERT INTO generos (id, nombre) VALUES (DEFAULT, 'Ciencia ficcion');
INSERT INTO generos (id, nombre) VALUES (DEFAULT, 'Terror');
INSERT INTO generos (id, nombre) VALUES (DEFAULT, 'Animacion');

INSERT INTO anios (id, anio) VALUES (DEFAULT, 2018);
INSERT INTO anios (id, anio) VALUES (DEFAULT, 2023);
INSERT INTO anios (id, anio) VALUES (DEFAULT, 2024);

INSERT INTO repartos (id, nombre, sexo, fecha_nacimiento) VALUES (DEFAULT, 'Marta Sanchez', 'Mujer', '1992-11-04');
INSERT INTO repartos (id, nombre, sexo, fecha_nacimiento) VALUES (DEFAULT, 'David Torres', 'Hombre', '1988-05-19');
INSERT INTO repartos (id, nombre, sexo, fecha_nacimiento) VALUES (DEFAULT, 'Elena Castro', 'Mujer', '1995-09-30');

INSERT INTO peliculas (id, titulo, calificacion, duracion, genero_id, anio_id) VALUES (DEFAULT, 'Viaje estelar', 12, 118, 1, 2);
INSERT INTO peliculas (id, titulo, calificacion, duracion, genero_id, anio_id) VALUES (DEFAULT, 'Noche sin fin', 18, 97, 2, 1);
INSERT INTO peliculas (id, titulo, calificacion, duracion, genero_id, anio_id) VALUES (DEFAULT, 'Mundo de colores', 0, 90, 3, 3);

INSERT INTO peliculas_reparto (pelicula_entity_id, reparto_id) VALUES (1, 1);
INSERT INTO peliculas_reparto (pelicula_entity_id, reparto_id) VALUES (1, 2);
INSERT INTO peliculas_reparto (pelicula_entity_id, reparto_id) VALUES (2, 3);
INSERT INTO peliculas_reparto (pelicula_entity_id, reparto_id) VALUES (3, 1);
INSERT INTO peliculas_reparto (pelicula_entity_id, reparto_id) VALUES (3, 3);