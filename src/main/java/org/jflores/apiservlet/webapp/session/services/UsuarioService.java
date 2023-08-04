package org.jflores.apiservlet.webapp.session.services;

import org.jflores.apiservlet.webapp.session.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(long id);
    void guardar(Usuario usuario);
    void eliminar(long id);
    Optional<Usuario> login(String username, String password);
}
