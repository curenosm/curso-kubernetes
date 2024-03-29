package org.curenosm.springcloud.msvc.cursos.service;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;

import java.util.List;
import java.util.Optional;

/**
 * Interface which represents the business logic contracts for our microservice
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
public interface CursoService {

    List<Curso> findAll();

    Optional<Curso> buscarPorIdConUsuarios(Long id, String token);

    Optional<Curso> findById(Long id);


    Optional<Curso> findByNombre(String nombre);

    Curso save(Curso curso);

    void deleteById(Long id);

    void deleteCursoUsuarioById(Long id);

    Optional<Usuario> assignUser(Usuario usuario, Long id, String token);

    Optional<Usuario> createUser(Usuario usuario, Long cursoId, String token);

    Optional<Usuario> deleteUser(Usuario usuario, Long cursoId, String token);

}
