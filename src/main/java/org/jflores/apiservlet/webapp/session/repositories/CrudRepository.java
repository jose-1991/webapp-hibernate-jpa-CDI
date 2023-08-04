package org.jflores.apiservlet.webapp.session.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> listar()throws Exception;
    T porId(Long id)throws Exception;
    void guardar(T t)throws Exception;
    void eliminar(Long id) throws Exception;

}
