package org.jflores.apiservlet.webapp.session.controllers;

import org.jflores.apiservlet.webapp.session.models.Usuario;
import org.jflores.apiservlet.webapp.session.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/registro/form")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(connection);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        Usuario usuario = null;
        if (id >0){
            Optional<Usuario> usuarioOptional = service.porId(id);
            if (usuarioOptional.isPresent()){
                usuario = usuarioOptional.get();
            }
        }
        req.setAttribute("usuario", usuario);
        req.setAttribute("tittle", req.getAttribute("tittle")+": Registro");
        getServletContext().getRequestDispatcher("/registro.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(connection);


    }



}
