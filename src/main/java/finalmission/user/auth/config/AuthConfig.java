package finalmission.user.auth.config;

import finalmission.user.auth.interceptor.AuthorizationInterceptor;
import finalmission.user.auth.resolver.AuthorizationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class AuthConfig implements WebMvcConfigurer {

    private final AuthorizationResolver authorizationResolver;
    private final AuthorizationInterceptor authorizationInterceptor;

    public AuthConfig(AuthorizationResolver authorizationResolver, AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationResolver = authorizationResolver;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizationResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/signup");
    }
}
