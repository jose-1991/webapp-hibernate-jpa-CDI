package org.jflores.apiservlet.webapp.session.repositories;

import org.jflores.apiservlet.webapp.session.configs.MysqlConn;
import org.jflores.apiservlet.webapp.session.configs.Repository;
import org.jflores.apiservlet.webapp.session.models.Categoria;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection connection;

    @Inject
    public CategoriaRepositoryImpl(@MysqlConn Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorias")) {
            while (resultSet.next()){
                Categoria categoria = getCategoria(resultSet);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM categorias AS c WHERE c.id=?")){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    categoria = getCategoria(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(long id) throws SQLException {

    }
    private static Categoria getCategoria(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(resultSet.getString("nombre"));
        categoria.setId(resultSet.getLong("id"));
        return categoria;
    }
}
