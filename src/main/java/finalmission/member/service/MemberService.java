package finalmission.member.service;

import finalmission.exception.NotFoundException;
import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.controller.dto.SignUpRequest;
import finalmission.member.controller.dto.SignUpResponse;
import finalmission.member.entity.Member;
import finalmission.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public SignUpResponse signup(SignUpRequest signUpRequest) {
        Member member = signUpRequest.toMember();
        Member savedMember = memberRepository.save(member);
        return SignUpResponse.from(savedMember);
    }

    public void login(LoginRequest loginRequest) {
        String nickname = loginRequest.nickname();
        memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다"));
    }

}
