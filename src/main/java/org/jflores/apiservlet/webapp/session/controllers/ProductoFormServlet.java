package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.configs.ProductoServicePrincipal;
import org.jflores.apiservlet.webapp.session.models.entities.Categoria;
import org.jflores.apiservlet.webapp.session.models.entities.Producto;
import org.jflores.apiservlet.webapp.session.services.ProductoService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()){
                producto = o.get();
            }
        }
        req.setAttribute("categorias", service.listarCategoria());
        req.setAttribute("producto", producto);
        req.setAttribute("tittle",req.getAttribute("tittle") + ": Formulario de productos");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");

        int precio;
        try {
            precio = Integer.parseInt(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }
        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        long categoriaId;
        try {
            categoriaId = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.trim().isEmpty()) {
            errores.put("nombre", "El nombre es requerido!");
        }
        if (sku == null || sku.trim().isEmpty()) {
            errores.put("sku", "El sku es requerido!");
        } else if (sku.length() > 10) {
            errores.put("sku", "El sku debe tener maximo 10 caracteres");
        }
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            errores.put("fecha_registro", "La fecha es requerida!");
        }
        if (precio == 0) {
            errores.put("precio", "El precio es requerido!");
        }
        if (categoriaId == 0) {
            errores.put("categoria", "La categoria es requerida!");
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeParseException e){
            fecha = null;
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = null;
        }
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);
        if (errores.isEmpty()) {

            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);
            req.setAttribute("tittle",req.getAttribute("tittle") + ": Formulario de productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
