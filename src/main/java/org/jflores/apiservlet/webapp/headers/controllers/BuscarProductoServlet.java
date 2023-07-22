package org.jflores.apiservlet.webapp.headers.controllers;

import org.jflores.apiservlet.webapp.headers.models.Producto;
import org.jflores.apiservlet.webapp.headers.services.ProductoService;
import org.jflores.apiservlet.webapp.headers.services.ProductoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");

        Optional<Producto> encontrado =
                service.listar().stream().filter(p -> {
                    if (nombre == null || nombre.isEmpty()){
                        return false;
                    }
                  return   p.getNombre().contains(nombre);
                }).findFirst();
        if (encontrado.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
                    try(PrintWriter out = resp.getWriter()) {

                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("       <head>");
                        out.println("               <meta charset=\"UTF-8\">");
                        out.println("               <title>Producto encontrado</title>");
                        out.println("       </head>");
                        out.println("       <body>");
                        out.println("               <h1>Producto encontrado!</h1>");
                        out.println("               <h3>Producto encontrado "+encontrado.get().getNombre()+"</h3>");
                        out.println("       </body>");
                        out.println("</html>");
                    }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos, no se encontro el producto que busca");
        }
    }
}
