package org.curenosm.springcloud.msvc.cursos;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Data class which is used for testing purposes only
 *
 * @version 1.0.0
 * @author Misael Cureño
 */
public class Datos {

    public static final Curso CURSO = new Curso(1L, "Ciencias", new ArrayList<>(), new ArrayList<>());

    public final static List<Curso> CURSOS = Arrays.asList(
            crearCurso001().orElseThrow(),
            crearCurso002().orElseThrow()
    );

    public static Optional<Curso> crearCurso001() {
        return Optional.of(
                Curso.builder()
                        .id(1L)
                        .nombre("Ciencias")
                        .usuarios(new ArrayList<>())
                        .build()
        );
    }

    public static Optional<Curso> crearCurso002() {
        return Optional.of(
                Curso.builder()
                        .id(2L)
                        .nombre("Mate")
                        .usuarios(new ArrayList<>())
                        .build()
        );
    }
}
