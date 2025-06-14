package finalmission.member.controller.dto;

import finalmission.member.entity.Member;

public record SignUpRequest(String nickname, String password) {
    public Member toMember() {
        return new Member(nickname, password);
    }
}
