package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.models.Producto;
import org.jflores.apiservlet.webapp.session.services.*;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(connection);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //parametros para el jsp
        req.setAttribute("productos", productos);
        req.setAttribute("username",usernameOptional);
        //para que ejecute la vista jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
    }
}
