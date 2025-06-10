package finalmission.user.auth.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CookieTokenAuthorizationHandler {

    private static final String TOKEN_NAME = "token";

    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(TOKEN_NAME))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    public void setToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(TOKEN_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void remove(HttpServletResponse response) {
        Cookie cookie = new Cookie(TOKEN_NAME, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
