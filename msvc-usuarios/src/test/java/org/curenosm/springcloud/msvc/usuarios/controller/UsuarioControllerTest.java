package org.curenosm.springcloud.msvc.usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.curenosm.springcloud.msvc.usuarios.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.curenosm.springcloud.msvc.usuarios.Datos.crearUsuario001;
import static org.curenosm.springcloud.msvc.usuarios.Datos.crearUsuario002;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListar() throws Exception {
        List<Usuario> usuarios = Arrays.asList(
                crearUsuario001().orElseThrow(),
                crearUsuario002().orElseThrow()
        );

        when(service.findAll()).thenReturn(usuarios);

        mvc.perform(
                        get("/")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Misael"))
                .andExpect(jsonPath("$[1].nombre").value("Pepe"))
        ;

        verify(service).findAll();

    }

}