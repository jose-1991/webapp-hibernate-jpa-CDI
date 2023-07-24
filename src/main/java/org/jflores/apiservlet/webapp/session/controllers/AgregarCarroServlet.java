package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.models.Carro;
import org.jflores.apiservlet.webapp.session.models.ItemCarro;
import org.jflores.apiservlet.webapp.session.models.Producto;
import org.jflores.apiservlet.webapp.session.services.ProductoService;
import org.jflores.apiservlet.webapp.session.services.ProductoServiceImpl;
import org.jflores.apiservlet.webapp.session.services.ProductoServiceJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Connection connection = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(connection);
        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath()+"/carro/ver");
    }
}
