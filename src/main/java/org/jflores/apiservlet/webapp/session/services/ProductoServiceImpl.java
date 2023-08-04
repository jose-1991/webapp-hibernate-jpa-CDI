package org.jflores.apiservlet.webapp.session.services;

import jakarta.inject.Inject;
import org.jflores.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jflores.apiservlet.webapp.session.configs.Service;
import org.jflores.apiservlet.webapp.session.interceptors.TransactionalJpa;
import org.jflores.apiservlet.webapp.session.models.entities.Categoria;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;
import org.jflores.apiservlet.webapp.session.repositories.CrudRepository;
import org.jflores.apiservlet.webapp.session.repositories.RepositoryJpa;


import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService{
    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> repositoryJdbc;

    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> repositoryCategoriaJdbc;

    @Override
    public List<Producto> listar() {
        try{
            return repositoryJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
