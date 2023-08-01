package org.jflores.apiservlet.webapp.session.interceptors;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocation) throws Exception {
        log.info(" ***** entrando antes de invocar el metodo "+ invocation.getMethod().getName() + "de la clase "+
                invocation.getMethod().getDeclaringClass());
        Object resultado = invocation.proceed();

        log.info("***** saliendo de la invocacion del metodo "+ invocation.getMethod().getName());
        return resultado;
    }
}
