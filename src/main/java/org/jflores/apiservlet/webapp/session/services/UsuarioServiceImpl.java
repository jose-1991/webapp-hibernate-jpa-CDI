package org.jflores.apiservlet.webapp.session.services;

import jakarta.inject.Inject;
import org.jflores.apiservlet.webapp.session.configs.Service;
import org.jflores.apiservlet.webapp.session.interceptors.TransactionalJpa;
import org.jflores.apiservlet.webapp.session.models.entities.Usuario;
import org.jflores.apiservlet.webapp.session.repositories.RepositoryJpa;
import org.jflores.apiservlet.webapp.session.repositories.UsuarioRepository;


import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;
    @Inject
    public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(long id) {
        try {
            return Optional.ofNullable(usuarioRepository.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(long id) {
        try {
            usuarioRepository.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
