package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jflores.apiservlet.webapp.session.models.Producto;
import org.jflores.apiservlet.webapp.session.services.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
