package org.curenosm.springcloud.msvc.cursos.repository;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * Data Access Object to deal with courses
 *
 * @version 1.0.0
 * @author Misael Cureño
 */
public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Modifying
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);

    Optional<Curso> findByNombre(String nombre);

}
