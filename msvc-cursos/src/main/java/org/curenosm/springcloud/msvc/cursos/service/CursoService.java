package org.curenosm.springcloud.msvc.cursos.service;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();

    Optional<Curso> buscarPorIdConUsuarios(Long id);
    Optional<Curso> buscarPorId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuarioPorId(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long id);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
