package org.jflores.apiservlet.webapp.session.configs;

import org.jflores.apiservlet.webapp.session.interceptors.Logging;
import org.jflores.apiservlet.webapp.session.interceptors.TrasactionalJdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TrasactionalJdbc
@Logging
@ApplicationScoped
@Stereotype
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Named
public @interface Service {
}
