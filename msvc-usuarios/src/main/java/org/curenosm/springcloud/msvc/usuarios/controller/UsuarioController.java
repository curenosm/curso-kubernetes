package org.curenosm.springcloud.msvc.usuarios.controller;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.curenosm.springcloud.msvc.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
// @RequestMapping("/api")
public class UsuarioController {

    private final Environment env;

    private final UsuarioService service;
    private final ApplicationContext context;

    @Autowired
    public UsuarioController(UsuarioService service, ApplicationContext context, Environment env) {
        this.service = service;
        this.context = context;
        this.env = env;
    }

    @GetMapping("/crash")
    public void crash() {
        ((ConfigurableApplicationContext) context).close();
    }

    @GetMapping("/")
    public Map<String, Object> listar() {

        Map<String, Object> body = new HashMap<>();
        body.put("users", service.findAll());
        body.put("pod_info", env.getProperty("POD_NAME") + ": " + env.getProperty("POD_IP"));
        body.put("texto", env.getProperty("config.texto"));
//        return Collections.singletonMap("users", service.findAll());
        return body;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);

        if (o.isPresent())
            return ResponseEntity.ok(o.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public Usuario crear(@RequestBody Usuario usuario) {
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (result.hasErrors())
            return validar(result);

        if (!usuario.getEmail().isEmpty() && service.exists(usuario.getEmail()))
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electrónico!")
            );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario,
                                    BindingResult result,
                                    @PathVariable Long id) {

        if (result.hasErrors())
            return validar(result);

        Optional<Usuario> o = service.findById(id);

        if (o.isPresent()) {
            Usuario usuarioDb = o.get();

            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            if (!usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail())
                    && service.findByEmail(usuario.getEmail()).isPresent())
                return ResponseEntity.badRequest().body(
                        Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electrónico!")
                );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(usuarioDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Optional<Usuario> o = service.findById(id);

        if (o.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllByIds(ids));
    }


    /**
     * Handler that according to the authorization code returns it to the view
     * @param code
     * @return
     */
    @GetMapping("/authorized")
    public Map<String, Object> authorized(@RequestParam String code) {
        return Collections.singletonMap("code", code);
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(
                err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }


}
