package org.curenosm.springcloud.msvc.usuarios.repository;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // Los parametros empiezan en 1, 2, ...
    @Query("select u from Usuario u where u.email=?1")
    Optional<Usuario> buscarPorEmail(String email);

    boolean existsByEmail(String email);

}
