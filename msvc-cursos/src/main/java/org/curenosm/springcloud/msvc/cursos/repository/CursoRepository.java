package org.curenosm.springcloud.msvc.cursos.repository;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
