package org.jflores.apiservlet.webapp.session.controllers;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jflores.apiservlet.webapp.session.models.entities.Usuario;
import org.jflores.apiservlet.webapp.session.services.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/registro/form")
public class RegistroServlet extends HttpServlet {

    @Inject
    UsuarioService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Map<String, String> erroresRegistro = new HashMap<>();
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (username == null || username.trim().isEmpty()){
            erroresRegistro.put("username", "El username es requerido!");
        }
        if (password == null || password.trim().isEmpty()){
            erroresRegistro.put("password", "El password es requerido!");
        }
        if (email == null || email.trim().isEmpty()){
            erroresRegistro.put("email", "El email es requerido!");
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        if (erroresRegistro.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath());
        }else {
            req.setAttribute("erroresRegistro", erroresRegistro);
            req.setAttribute("usuario", usuario);
            getServletContext().getRequestDispatcher("/registro.jsp").forward(req,resp);
        }

    }



}
