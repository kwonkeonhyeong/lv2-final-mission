package finalmission.user.controller.dto.request;

import finalmission.user.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @Email
        String email,
        @NotBlank
        String password
) {
    public Member toMember() {
        return new Member(email, password);
    }
}
