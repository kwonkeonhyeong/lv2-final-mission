package finalmission.user.controller;

import finalmission.user.auth.handler.CookieTokenAuthorizationHandler;
import finalmission.user.controller.dto.request.LoginRequest;
import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.controller.dto.response.SignUpResponse;
import finalmission.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final CookieTokenAuthorizationHandler cookieTokenAuthorizationHandler;

    public UserController(UserService userService, CookieTokenAuthorizationHandler cookieTokenAuthorizationHandler) {
        this.userService = userService;
        this.cookieTokenAuthorizationHandler = cookieTokenAuthorizationHandler;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        SignUpResponse response = userService.signUp(request);
        // todo : 회원 가입 시 응답 code 다시 찾아보기
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpServletResponse response, @RequestBody @Valid LoginRequest request) {
        String token = userService.login(request);
        cookieTokenAuthorizationHandler.setToken(response, token);
        return ResponseEntity.noContent().build();
    }
}
