package org.curenosm.springcloud.msvc.cursos.controller;

import feign.FeignException;
import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.curenosm.springcloud.msvc.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 * Controller class that handles all the requests related to the courses
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
@RestController
public class CursoController {

    private final CursoService service;

    @Autowired
    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(service.findAll());
    }


    /**
     * Interface which Feign uses to generate a client that we can use to interact with
     * users microservice.
     *
     * @param id Identifier of the course
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Curso> o = service.buscarPorIdConUsuarios(id);

        if (o.isPresent())
            return ResponseEntity.ok(o.get());

        return ResponseEntity.notFound().build();
    }

    /**
     * Interface which Feign uses to generate a client that we can use to interact with
     * users microservice.
     *
     * @param curso  Course to create
     * @param result Injected object to help with validation
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors())
            return validar(result);

        Curso cursoDB = service.save(curso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoDB);
    }

    /**
     * Interface which Feign uses to generate a client that we can use to interact with
     * users microservice.
     *
     * @param id     Identifier of the course
     * @param curso  Information to save of the course
     * @param result Injected object to help with validation
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @Valid @RequestBody Curso curso,
                                    BindingResult result) {
        if (result.hasErrors())
            return validar(result);

        Optional<Curso> o = service.findById(id);

        if (o.isPresent()) {
            Curso cursoDB = o.get();
            cursoDB.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
        }

        return ResponseEntity.notFound().build();
    }


    /**
     * Handler to delete a course by its id
     *
     * @param id Identifier of the course
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> o = service.findById(id);

        if (o.isPresent()) {
            service.deleteById(o.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    /**
     * Handler to assign a User to a course
     *
     * @param usuario User to be assigned to a given course.
     * @param cursoId Information to save of the course.
     */
    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario,
                                          @PathVariable Long cursoId) {
        Optional<Usuario> o;

        try {
            o = service.createUser(usuario, cursoId);
        } catch (FeignException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap("mensaje", "No se pudo crear el usuario o error en la comunicacion " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }


    /**
     * Handler to delete a user
     *
     * @param usuario User to be assigned to a given course.
     * @param cursoId Information to save of the course
     */
    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario,
                                             @PathVariable Long cursoId) {
        Optional<Usuario> o;

        try {
            o = service.deleteUser(usuario, cursoId);
        } catch (FeignException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap("mensaje", "No existe el usuario o error en la comunicacion " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Handler to remove a user from a given course
     *
     * @param id Identifier of the course to be deleted
     */
    @DeleteMapping("/eliminar-curso-usuario/{id}")
    public ResponseEntity<?> eliminarCursoUsuarioPorId(@PathVariable Long id) {
        service.deleteCursoUsuarioById(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Method to validate properly
     *
     * @param result Object to inject for validation purposes
     */
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err ->
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }
}
