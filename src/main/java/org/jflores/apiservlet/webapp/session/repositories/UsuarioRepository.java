package org.jflores.apiservlet.webapp.session.repositories;

import org.jflores.apiservlet.webapp.session.models.entities.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws Exception;
}
