package org.jflores.apiservlet.webapp.session.repositories;

import org.jflores.apiservlet.webapp.session.models.Categoria;
import org.jflores.apiservlet.webapp.session.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto>{
    private Connection connection;

    public ProductoRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                    "INNER JOIN categorias as c ON p.categoria_id = c.id ORDER BY p.id ASC")) {
            while (resultSet.next()){
                Producto producto = getProducto(resultSet);
                productos.add(producto);
            }

        }
        return productos;
    }

    @Override
    public Producto porId(long id) throws SQLException {
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
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() > 0){
            sql = "UPDATE productos set nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        }else {
            sql = "INSERT INTO productos(nombre, precio, sku, categoria_id, fecha_registro) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,producto.getNombre());
            statement.setInt(2,producto.getPrecio());
            statement.setString(3, producto.getSku());
            statement.setLong(4,producto.getCategoria().getId());
            if (producto.getId() > 0) {
                statement.setLong(5, producto.getId());
            }else {
                statement.setDate(5,Date.valueOf(producto.getFechaRegistro()));
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setSku(resultSet.getString("sku"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("categoria_id"));
        categoria.setNombre(resultSet.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }
}
