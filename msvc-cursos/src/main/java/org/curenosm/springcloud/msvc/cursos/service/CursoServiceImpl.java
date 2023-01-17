package org.curenosm.springcloud.msvc.cursos.service;

import lombok.AllArgsConstructor;
import org.curenosm.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.curenosm.springcloud.msvc.cursos.model.entities.CursoUsuario;
import org.curenosm.springcloud.msvc.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Implementation of the CursoService interface to implement the business logic
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    // It's not necessary to mark them as Autowired because there exists an all args constructor
    private CursoRepository repository;

    private UsuarioClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Curso> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> assignUser(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {
            Usuario usuarioMsvc = client.buscar(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.getCursoUsuarios().add(cursoUsuario);
            repository.save(curso);

            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> createUser(Usuario usuario, Long cursoId) {

        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {
            Usuario usuarioNuevoMsvc = client.crear(usuario);

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());
            curso.getCursoUsuarios().add(cursoUsuario);
            repository.save(curso);

            return Optional.of(usuarioNuevoMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> deleteUser(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {
            Usuario usuarioMsvc = client.buscar(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.getCursoUsuarios().remove(cursoUsuario);
            repository.save(curso);

            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> buscarPorIdConUsuarios(Long id) {
        Optional<Curso> o = repository.findById(id);

        if (o.isPresent()) {
            Curso curso = o.get();

            if (!curso.getCursoUsuarios().isEmpty()) {
                List<Long> ids = curso.getCursoUsuarios()
                        .stream()
                        .map(CursoUsuario::getUsuarioId)
                        .toList();
                // .collect(Collectors.toList())

                List<Usuario> usuarios = client.obtenerAlumnosPorCurso(ids);
                curso.setUsuarios(usuarios);

            }

            return Optional.of(curso);
        }

        return Optional.empty();
    }

    @Override
    public void deleteCursoUsuarioById(Long id) {
        repository.eliminarCursoUsuarioPorId(id);
    }
}
