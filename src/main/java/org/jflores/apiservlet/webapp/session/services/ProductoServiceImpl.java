package org.jflores.apiservlet.webapp.session.services;

import org.jflores.apiservlet.webapp.session.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

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
}
