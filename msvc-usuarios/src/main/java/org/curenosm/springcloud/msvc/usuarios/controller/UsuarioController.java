package org.curenosm.springcloud.msvc.usuarios.controller;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.curenosm.springcloud.msvc.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
// @RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Usuario> o = service.buscarPorId(id);

        if (o.isPresent())
            return ResponseEntity.ok(o.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public Usuario crear(@RequestBody Usuario usuario) {
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (service.buscarPorEmail(usuario.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(
                Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electrónico!")
            );

        if (result.hasErrors())
            return validar(result);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario,
                                    BindingResult result,
                                    @PathVariable Long id) {

        if (result.hasErrors())
            return validar(result);

        Optional<Usuario> o = service.buscarPorId(id);

        if (o.isPresent()) {
            Usuario usuarioDb = o.get();

            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            if (!usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail())
                    && service.buscarPorEmail(usuario.getEmail()).isPresent())
                return ResponseEntity.badRequest().body(
                        Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electrónico!")
                );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.guardar(usuarioDb));
        }



        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Optional<Usuario> o = service.buscarPorId(id);

        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(
            err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }

}
