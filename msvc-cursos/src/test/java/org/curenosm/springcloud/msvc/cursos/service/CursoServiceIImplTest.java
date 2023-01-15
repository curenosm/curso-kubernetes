package org.curenosm.springcloud.msvc.cursos.service;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.curenosm.springcloud.msvc.cursos.repository.CursoRepository;
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

import org.curenosm.springcloud.msvc.cursos.Datos;

@ExtendWith({MockitoExtension.class})
public class CursoServiceIImplTest {

    @Mock
    CursoRepository repository;

    @InjectMocks
    CursoServiceImpl service;

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
    void findProductoPorNombre() {

        when(repository.findByNombre(anyString())).thenReturn(Datos.crearCurso001());
        Optional<Curso> producto = service.findByNombre("Ciencias");


        assertTrue(producto.isPresent());
        assertEquals(1L, producto.orElseThrow().getId());
        assertEquals("Ciencias", producto.get().getNombre());
    }


    @AfterAll
    static void afterAll() {
        System.out.println("Finishing test class");
    }


}
