package org.curenosm.springcloud.msvc.usuarios.repository;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
