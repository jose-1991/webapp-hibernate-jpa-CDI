package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jflores.apiservlet.webapp.session.models.Carro;
import org.jflores.apiservlet.webapp.session.models.ItemCarro;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;
import org.jflores.apiservlet.webapp.session.services.ProductoService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
//            HttpSession session = req.getSession();
//            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
