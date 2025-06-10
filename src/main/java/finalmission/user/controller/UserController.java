package finalmission.user.controller;

import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.controller.dto.response.SignUpResponse;
import finalmission.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        SignUpResponse response = userService.signUp(request);
        // todo : 회원 가입 시 응답 code 다시 찾아보기
        return ResponseEntity.ok(response);
    }
}
