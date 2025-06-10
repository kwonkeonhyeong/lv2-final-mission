package finalmission.user.service;

import finalmission.exception.UnauthorizedException;
import finalmission.user.auth.provider.AuthorizationPayload;
import finalmission.user.auth.provider.JwtAuthorizationProvider;
import finalmission.user.controller.dto.request.LoginRequest;
import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.controller.dto.response.SignUpResponse;
import finalmission.user.entity.Member;
import finalmission.user.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserService {
    private final MemberRepository memberRepository;
    private final JwtAuthorizationProvider jwtAuthorizationProvider;

    public UserService(MemberRepository memberRepository, JwtAuthorizationProvider jwtAuthorizationProvider) {
        this.memberRepository = memberRepository;
        this.jwtAuthorizationProvider = jwtAuthorizationProvider;
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        Member member = request.toMember();
        Member savedMember = memberRepository.save(member);
        return SignUpResponse.from(savedMember);
    }

    public String login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email()).orElseThrow(
                () -> new UnauthorizedException("인증되지 않은 유저 정보입니다.")
        );
        if (checkInvalidLogin(member, request)) {
            throw new UnauthorizedException("인증되지 않은 유저 정보입니다.");
        }
        return jwtAuthorizationProvider.createToken(new AuthorizationPayload(member.getEmail()));
    }

    private boolean checkInvalidLogin(Member member, LoginRequest request) {
        return member.checkInvalidLogin(request.email(), request.password());
    }
}
