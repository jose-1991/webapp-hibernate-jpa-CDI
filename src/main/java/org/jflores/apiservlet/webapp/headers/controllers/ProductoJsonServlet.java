package org.jflores.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jflores.apiservlet.webapp.headers.models.Producto;
import org.jflores.apiservlet.webapp.headers.services.ProductoService;
import org.jflores.apiservlet.webapp.headers.services.ProductoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);

        resp.setContentType("text/html;charset=UTF-8");
                try(PrintWriter out = resp.getWriter()) {

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("       <head>");
                    out.println("               <meta charset=\"UTF-8\">");
                    out.println("               <title>Detalle de producto desde json</title>");
                    out.println("       </head>");
                    out.println("       <body>");
                    out.println("               <h1>Detalle de producto desde json!</h1>");
                    out.println("<ul>");
                    out.println("   <li>Id: "+producto.getId()+"</li>");
                    out.println("   <li>Nombre: "+producto.getNombre()+"</li>");
                    out.println("   <li>Tipo: "+producto.getTipo()+"</li>");
                    out.println("   <li>Precio: "+producto.getPrecio()+"</li>");
                    out.println("</ul>");
                    out.println("       </body>");
                    out.println("</html>");
                }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
