package org.curenosm.springcloud.msvc.cursos.service;

import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> buscarPorId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
