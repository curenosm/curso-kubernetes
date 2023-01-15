package org.curenosm.springcloud.msvc.usuarios.service;


import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.curenosm.springcloud.msvc.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.curenosm.springcloud.msvc.usuarios.Datos;

@ExtendWith({MockitoExtension.class})
public class UsuarioServiceImplTest {

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioServiceImpl service;

    @Captor
    ArgumentCaptor<Long> captor;

    private TestInfo testInfo;

    private TestReporter testReporter;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Initializing test");
    }

    @BeforeEach
    void initMethodTest(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        System.out.println(testInfo.getDisplayName() + " " + testInfo.getTestClass() + " "
                + testInfo.getTestMethod()
                + " with labels: " + testInfo.getTags());

        testReporter.publishEntry("Executing:" + testInfo.getTags());
        System.out.println("Initializing");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Ending test method");
    }

    //    @Disabled
    @Tag("dev")
    @Test
    @DisplayName("Probando el metodo para encontrar un producto por su nombre")
    void findUsuarioPorNombre() {

        when(repository.findByEmail(anyString())).thenReturn(Datos.crearUsuario001());
        Optional<Usuario> usuario = service.findByEmail("Ciencias");


        assertTrue(usuario.isPresent());
        assertEquals(1L, usuario.orElseThrow().getId());
        assertEquals("Ciencias", usuario.get().getNombre());
    }


    @AfterAll
    static void afterAll() {
        System.out.println("Finishing test class");
    }


}
