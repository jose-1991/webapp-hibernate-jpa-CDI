package org.jflores.apiservlet.webapp.headers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String methodHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("       <head>");
            out.println("               <meta charset=\"UTF-8\">");
            out.println("               <title>Cabeceras HTTP request</title>");
            out.println("       </head>");
            out.println("       <body>");
            out.println("               <h1>Cabeceras HTTP request!</h1>");
            out.println("               <ul>");
            out.println("                       <li>metodo http: " + methodHttp + "</li>");
            out.println("                       <li>request uri: " + requestUri + "</li>");
            out.println("                       <li>request url: " + requestUrl + "</li>");
            out.println("                       <li>context path: " + contextPath + "</li>");
            out.println("                       <li>servlet path: " + servletPath + "</li>");
            out.println("                       <li>ip: " + ip + "</li>");
            out.println("                       <li>ip cliente: " + ipCliente + "</li>");
            out.println("                       <li>puerto local: " + port + "</li>");
            out.println("                       <li>scheme: " + scheme + "</li>");
            out.println("                       <li>host: " + host + "</li>");
            out.println("                       <li>url: " + url + "</li>");
            out.println("                       <li>url2: " + url2 + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>" + cabecera + ":" + req.getHeader(cabecera) + "</li>");
            }
            out.println("               </ul>");
            out.println("       </body>");
            out.println("</html>");
        }
    }
}
