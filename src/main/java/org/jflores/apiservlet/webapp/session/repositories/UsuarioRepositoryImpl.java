package org.jflores.apiservlet.webapp.session.repositories;

import org.jflores.apiservlet.webapp.session.configs.MysqlConn;
import org.jflores.apiservlet.webapp.session.configs.Repository;
import org.jflores.apiservlet.webapp.session.models.entities.Usuario;

import jakarta.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJdbc
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    @MysqlConn
    Connection connection;

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios")) {
            while (resultSet.next()) {
                Usuario usuario = getUsuario(resultSet);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    private Usuario getUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setUsername(resultSet.getString("username"));
        usuario.setPassword(resultSet.getString("password"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setTipo(resultSet.getString("tipo"));
        return usuario;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE id=?")) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = getUsuario(resultSet);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() > 0) {
            sql = "UPDATE usuarios set username=?, password=?, email=?, tipo=? WHERE id=?";
        } else {
            sql = "INSERT INTO usuarios (username, password, email, tipo)VALUES(?,?,?,?)";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTipo());
            if (usuario.getId() > 0) {
                statement.setLong(5, usuario.getId());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE username=?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getLong("id"));
                    usuario.setUsername(resultSet.getString("username"));
                    usuario.setPassword(resultSet.getString("password"));
                    usuario.setEmail(resultSet.getString("email"));
                }
            }

        }
        return usuario;
    }
}
