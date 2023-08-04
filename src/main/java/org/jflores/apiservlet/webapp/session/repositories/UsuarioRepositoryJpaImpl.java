package org.jflores.apiservlet.webapp.session.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jflores.apiservlet.webapp.session.configs.Repository;
import org.jflores.apiservlet.webapp.session.models.entities.Usuario;


import java.util.List;

@RepositoryJpa
@Repository
public class UsuarioRepositoryJpaImpl implements UsuarioRepository{

    @Inject
    EntityManager em;

    @Override
    public List<Usuario> listar() throws Exception {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        return em.find(Usuario.class, id);
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() >0){
            em.merge(usuario);
        }else {
            em.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        return em.createQuery("select u from Usuario u where u.username=:username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
