package org.curenosm.springcloud.msvc.cursos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.curenosm.springcloud.msvc.cursos.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.curenosm.springcloud.msvc.cursos.Datos.crearCurso001;
import static org.curenosm.springcloud.msvc.cursos.Datos.crearCurso002;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CursoService service;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListar() throws Exception {
        List<Curso> cursos = Arrays.asList(
                crearCurso001().orElseThrow(),
                crearCurso002().orElseThrow()
        );

        when(service.findAll()).thenReturn(cursos);

        mvc.perform(
                        get("/")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ciencias"))
                .andExpect(jsonPath("$[1].nombre").value("Mate"))
        ;

        verify(service).findAll();

    }

}