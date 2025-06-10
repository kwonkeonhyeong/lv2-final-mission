package finalmission.user.service;

import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.controller.dto.response.SignUpResponse;
import finalmission.user.entity.Member;
import finalmission.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignUpResponse signUp(SignUpRequest request) {
        Member member = request.toMember();
        Member savedMember = userRepository.save(member);
        return SignUpResponse.from(savedMember);
    }
}
