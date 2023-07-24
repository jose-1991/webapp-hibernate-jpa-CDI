package org.jflores.apiservlet.webapp.session.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService{


    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
