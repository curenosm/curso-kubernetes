package org.curenosm.springcloud.msvc.usuarios.repository;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTest {


    @Autowired
    private UsuarioRepository repository;

    @Test
    void testFindById() {
        Optional<Usuario> usuario = repository.findById(1L);

        assertTrue(usuario.isPresent());
        assertEquals("Ciencias", usuario.orElseThrow().getNombre());
    }


    @Test
    void testFindAll() {
        List<Usuario> usuarios = (ArrayList<Usuario>) repository.findAll();
        assertFalse(usuarios.isEmpty());
        assertEquals(2, usuarios.size());
    }

}
