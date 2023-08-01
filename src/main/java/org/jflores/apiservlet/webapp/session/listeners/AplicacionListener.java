package org.jflores.apiservlet.webapp.session.listeners;

import org.jflores.apiservlet.webapp.session.models.Carro;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicializando la aplicacion!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "algun valor global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruyendo la aplicacion!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el Request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request");
        sre.getServletRequest().setAttribute("tittle", "Catalogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("detruyendo el Request!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("inicializando HttpSession");
//        Carro carro = new Carro();
//        HttpSession session = se.getSession();
//        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo HttpSession");
    }
}
