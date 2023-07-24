package org.jflores.apiservlet.webapp.session.repositories;

import org.jflores.apiservlet.webapp.session.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository{
    private Connection connection;

    public ProductoRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                    "INNER JOIN categorias as c ON p.categoria_id = c.id")) {
            while (resultSet.next()){
                Producto producto = getProducto(resultSet);

                productos.add(producto);
            }

        }
        return productos;
    }

    @Override
    public Object porId(long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT p.*, c.nombre as categoria FROM " +
                "productos AS p " +
                "INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            statement.setLong(1,id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    producto = getProducto(resultSet);
                }
            }

        }
        return producto;
    }

    @Override
    public void guardar(Object o) throws SQLException {

    }

    @Override
    public void eliminar(long id) throws SQLException {

    }

    private Producto getProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setTipo(resultSet.getString("categoria"));
        return producto;
    }
}
