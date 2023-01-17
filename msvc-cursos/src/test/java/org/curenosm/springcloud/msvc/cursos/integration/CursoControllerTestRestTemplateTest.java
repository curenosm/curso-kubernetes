package org.curenosm.springcloud.msvc.cursos.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate client;


    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    private String createUri(String uri) {
        return "http://localhost:" + this.port + uri;
    }

    @Test
    @Order(1)
    void listarTest() throws JsonProcessingException {
        ResponseEntity<Curso[]> res = client.getForEntity(
                createUri("/"),
                Curso[].class
        );

        List<Curso> cursos = Arrays.asList(res.getBody());

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, res.getHeaders().getContentType());

        assertEquals(2, cursos.size());

        assertEquals("Ciencias", cursos.get(0).getNombre());
        assertEquals(1L, cursos.get(0).getId());

        assertEquals("Mate", cursos.get(1).getNombre());
        assertEquals(2L, cursos.get(1).getId());

        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(res.getBody()));

        assertEquals("Mate", json.get(1).path("nombre").asText());
        assertEquals(2L, json.get(1).path("id").asLong());


    }

}
