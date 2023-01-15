package org.curenosm.springcloud.msvc.cursos.service;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> findAll();

    Optional<Curso> buscarPorIdConUsuarios(Long id);

    Optional<Curso> findById(Long id);


    Optional<Curso> findByNombre(String nombre);

    Curso save(Curso curso);

    void deleteById(Long id);

    void deleteCursoUsuarioById(Long id);

    Optional<Usuario> assignUser(Usuario usuario, Long id);

    Optional<Usuario> createUser(Usuario usuario, Long cursoId);

    Optional<Usuario> deleteUser(Usuario usuario, Long cursoId);

}
