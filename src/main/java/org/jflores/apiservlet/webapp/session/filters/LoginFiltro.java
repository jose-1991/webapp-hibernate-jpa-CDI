package org.jflores.apiservlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jflores.apiservlet.webapp.session.services.LoginService;
import org.jflores.apiservlet.webapp.session.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*", "/productos/form/*","/productos/eliminar/*"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) servletRequest);
        if (username.isPresent()){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "no estas autorizado para ingresar a esta pagina!");
        }
    }
}
