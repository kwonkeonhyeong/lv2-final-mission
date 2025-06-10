package finalmission.user.auth.interceptor;

import finalmission.user.auth.handler.CookieTokenAuthorizationHandler;
import finalmission.user.auth.provider.AuthorizationPayload;
import finalmission.user.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final CookieTokenAuthorizationHandler cookieTokenAuthorizationHandler;
    private final JwtAuthorizationProvider jwtAuthorizationProvider;

    public AuthorizationInterceptor(CookieTokenAuthorizationHandler cookieTokenAuthorizationHandler, JwtAuthorizationProvider jwtAuthorizationProvider) {
        this.cookieTokenAuthorizationHandler = cookieTokenAuthorizationHandler;
        this.jwtAuthorizationProvider = jwtAuthorizationProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = cookieTokenAuthorizationHandler.getToken(request);
        if (token.isEmpty()) {
            request.setAttribute("authorizationPayload", null);
            return true;
        }

        jwtAuthorizationProvider.validateToken(token);

        AuthorizationPayload payload = jwtAuthorizationProvider.getPayload(token);
        request.setAttribute("authorizationPayload", payload);
        return true;
    }
}
