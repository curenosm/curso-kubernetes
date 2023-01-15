package org.curenosm.springcloud.msvc.cursos.clients;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @FeignClient(name="msvc-usuarios", url="localhost:8001")

/**
 * Interface which Feign uses to generate a client that we can use to interact with
 * users microservice.
 *
 * @version 1.0.0
 * @author Misael Cureño
 */
@FeignClient(name = "msvc-usuarios", url = "${msvc.usuarios.url}")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Usuario buscar(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
