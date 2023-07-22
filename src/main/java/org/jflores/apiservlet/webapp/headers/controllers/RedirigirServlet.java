package org.jflores.apiservlet.webapp.headers.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setHeader("Location",req.getContextPath()+"/productos.html");
//        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.sendRedirect(req.getContextPath()+"/productos.html");
    }
}
