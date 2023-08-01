package org.jflores.apiservlet.webapp.session.configs;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SessionScoped
@Named
@Stereotype
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface CarroCompra {
}
