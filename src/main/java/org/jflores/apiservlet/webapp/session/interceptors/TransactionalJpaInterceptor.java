package org.jflores.apiservlet.webapp.session.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.jflores.apiservlet.webapp.session.services.ServiceJdbcException;


import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        try {
            log.info(" -------> iniciando transaccion " + invocation.getMethod().getName()+
                    "de la clase "+ invocation.getMethod().getDeclaringClass().getName());
            em.getTransaction().begin();
            Object resultado = invocation.proceed();
            em.getTransaction().commit();
            log.info(" -------> finalizando transaccion " + invocation.getMethod().getName()+
                    "de la clase "+ invocation.getMethod().getDeclaringClass().getName());
            return resultado;
        } catch (ServiceJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
