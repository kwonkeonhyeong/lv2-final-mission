package finalmission.member.service;

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
}
