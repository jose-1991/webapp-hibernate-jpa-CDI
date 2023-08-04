package org.jflores.apiservlet.webapp.session.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jflores.apiservlet.webapp.session.configs.Repository;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;


import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements  CrudRepository<Producto> {
    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() throws Exception {
        return em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList(); // ==
        // select p from Producto p
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if (producto.getId() != null && producto.getId() >0){
            em.merge(producto);
        }else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}
