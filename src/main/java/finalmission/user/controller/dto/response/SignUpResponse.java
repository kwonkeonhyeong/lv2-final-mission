package finalmission.user.controller.dto.response;

import finalmission.user.entity.Member;

public record SignUpResponse(
        String email
) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(member.getEmail());
    }
}
