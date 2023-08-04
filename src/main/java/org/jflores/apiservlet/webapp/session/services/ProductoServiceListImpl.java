package org.jflores.apiservlet.webapp.session.services;

import org.jflores.apiservlet.webapp.session.models.entities.Categoria;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
public class ProductoServiceListImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado", "computacion", 40000));
    }

    @Override
    public Optional<Producto> porId(long id) {
        return listar().stream().filter(p-> p.getId() == id).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(long id) {
        return Optional.empty();
    }
}
