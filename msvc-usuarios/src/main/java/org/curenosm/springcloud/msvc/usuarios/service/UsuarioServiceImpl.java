package org.curenosm.springcloud.msvc.usuarios.service;

import org.curenosm.springcloud.msvc.usuarios.clients.CursoClienteRest;
import org.curenosm.springcloud.msvc.usuarios.model.entities.Usuario;
import org.curenosm.springcloud.msvc.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CursoClienteRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        client.eliminarCursoUsuarioPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
        // return repository.buscarPorEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByIds(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }
}
