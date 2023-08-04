package org.jflores.apiservlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.jflores.apiservlet.webapp.session.services.ServiceJdbcException;

import java.io.IOException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

//    @Inject
//    @MysqlConn
//    private Connection conn;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        try (Connection connRequest = this.conn) {
//            if (connRequest.getAutoCommit()) {
//                connRequest.setAutoCommit(false);
//            }
            try {
//                servletRequest.setAttribute("conn", connRequest);
                filterChain.doFilter(servletRequest, servletResponse);
//                connRequest.commit();
            } catch (ServiceJdbcException e) {
//                connRequest.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
