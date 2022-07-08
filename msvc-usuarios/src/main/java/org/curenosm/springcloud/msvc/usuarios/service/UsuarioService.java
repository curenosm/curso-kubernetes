package org.curenosm.springcloud.msvc.usuarios.service;

import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario>  buscarPorId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);

}
