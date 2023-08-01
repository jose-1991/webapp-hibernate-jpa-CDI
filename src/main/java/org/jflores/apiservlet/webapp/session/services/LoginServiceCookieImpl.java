package org.jflores.apiservlet.webapp.session.services;

import javax.enterprise.inject.Alternative;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

//@Alternative
public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals("username"))
                .map(Cookie::getValue)
                .findFirst();
    }
}
