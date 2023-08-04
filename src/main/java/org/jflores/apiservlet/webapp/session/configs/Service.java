package org.jflores.apiservlet.webapp.session.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.jflores.apiservlet.webapp.session.interceptors.Logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Logging
@ApplicationScoped
@Stereotype
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Named
public @interface Service {
}
