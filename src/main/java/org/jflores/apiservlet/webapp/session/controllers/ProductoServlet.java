package org.jflores.apiservlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jflores.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;
import org.jflores.apiservlet.webapp.session.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> productos = service.listar();
        Optional<String> usernameOptional = auth.getUsername(req);

        //parametros para el jsp
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("tittle", req.getAttribute("tittle") + ": Listado de productos");
        //para que ejecute la vista jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
