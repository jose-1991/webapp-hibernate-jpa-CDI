package org.jflores.apiservlet.webapp.session.interceptors;

import org.jflores.apiservlet.webapp.session.configs.MysqlConn;
import org.jflores.apiservlet.webapp.session.services.ServiceJdbcException;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.sql.Connection;
import java.util.logging.Logger;

@TrasactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }
        try {
            log.info(" -------> iniciando transaccion " + invocation.getMethod().getName()+
                    "de la clase "+ invocation.getMethod().getDeclaringClass().getName());
            Object resultado = invocation.proceed();
            conn.commit();
            log.info(" -------> finalizando transaccion " + invocation.getMethod().getName()+
                    "de la clase "+ invocation.getMethod().getDeclaringClass().getName());
            return resultado;
        } catch (ServiceJdbcException e) {
            conn.rollback();
            throw e;
        }
    }

}
