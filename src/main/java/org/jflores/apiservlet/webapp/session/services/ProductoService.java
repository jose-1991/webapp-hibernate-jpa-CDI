package org.jflores.apiservlet.webapp.session.services;

import org.jflores.apiservlet.webapp.session.models.Categoria;
import org.jflores.apiservlet.webapp.session.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(long id);
    void guardar(Producto producto);
    void eliminar(long id);
    List<Categoria> listarCategoria();
    Optional<Categoria> porIdCategoria(long id);
}
