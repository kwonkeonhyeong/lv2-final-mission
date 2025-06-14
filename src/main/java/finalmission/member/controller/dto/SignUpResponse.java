package finalmission.member.controller.dto;

import finalmission.member.entity.Member;

public record SignUpResponse(String nickname) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(member.getNickname());
    }
}
