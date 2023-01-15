package org.curenosm.springcloud.msvc.cursos.repository;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    void testFindById() {
        Optional<Curso> curso = repository.findById(1L);

        assertTrue(curso.isPresent());
        assertEquals("Ciencias", curso.orElseThrow().getNombre());
    }


    @Test
    void testFindAll() {
        List<Curso> cursos = (ArrayList<Curso>) repository.findAll();
        assertFalse(cursos.isEmpty());
        assertEquals(2, cursos.size());
    }

    @Test
    void testSave() {
        // Given
        Curso curso1 = Curso.builder()
                .nombre("Letras")
                .cursoUsuarios(null)
                .usuarios(null)
                .build();
        Curso cuentaCreada = repository.save(curso1);

        // When
        Curso curso = repository.findByNombre("Letras").orElseThrow();

        // Then
        assertEquals("Letras", curso.getNombre());
        assertEquals(cuentaCreada.getId(), curso.getId());
    }

    @Test
    void testUpdate() {
        // Given
        Curso curso1 = Curso.builder()
                .nombre("Letras")
                .cursoUsuarios(null)
                .usuarios(null)
                .build();
        Curso cursoCreado = repository.save(curso1);

        // When
        Curso curso = repository.findByNombre("Letras").orElseThrow();
        curso.setNombre("Letras y Filo");
        // Then
        assertEquals("Letras", curso.getNombre());
        assertEquals(cursoCreado.getId(), curso.getId());

        // When
        Curso cursoActualizado = repository.save(curso);

        // Then
        assertEquals("Letras", cursoActualizado.getNombre());

    }

    @Test
    void testDelete() {
        Curso curso = repository.findById(2L).orElseThrow();
        assertEquals("Mate", curso.getNombre());

        repository.delete(curso);

        assertThrows(NoSuchElementException.class, () -> {
            // cuentaRepository.findByNombre("Carlos").orElseThrow();
            repository.findById(2L).orElseThrow();
        });

        assertEquals(1, repository.count());
        assertEquals(1, ((ArrayList<Curso>) repository.findAll()).size());

    }

}
