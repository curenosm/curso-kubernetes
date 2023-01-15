package org.curenosm.springcloud.msvc.usuarios;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Datos {


    public static final Usuario USUARIO = new Usuario(1L, "Misa", "curenosm@gmail.com", "123456");

    public final static List<Usuario> USUARIOS = Arrays.asList(
            crearUsuario001().orElseThrow(),
            crearUsuario002().orElseThrow()
    );

    public static Optional<Usuario> crearUsuario001() {
        return Optional.of(
                Usuario.builder()
                        .id(1L)
                        .nombre("Misael")
                        .email("curenosm@gmail.com")
                        .password("123456")
                        .build()
        );
    }

    public static Optional<Usuario> crearUsuario002() {
        return Optional.of(
                Usuario.builder()
                        .id(1L)
                        .nombre("Pepe")
                        .email("pepe@gmail.com")
                        .password("123456")
                        .build()
        );
    }
}
