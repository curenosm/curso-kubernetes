package org.curenosm.springcloud.msvc.usuarios.service;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void delete(Long id);

    Optional<Usuario> findByEmail(String email);

    boolean exists(String email);

    List<Usuario> findAllByIds(Iterable<Long> ids);

}
