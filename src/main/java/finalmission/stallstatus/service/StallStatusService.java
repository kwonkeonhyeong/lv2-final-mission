package finalmission.stallstatus.service;

import finalmission.exception.ConflictException;
import finalmission.exception.NotFoundException;
import finalmission.member.entity.Member;
import finalmission.member.repository.MemberRepository;
import finalmission.stall.entity.Stall;
import finalmission.stall.repository.StallRepository;
import finalmission.stallstatus.controller.dto.request.StallStatusCreateRequest;
import finalmission.stallstatus.controller.dto.response.StallStatusCreateResponse;
import finalmission.stallstatus.entity.StallStatus;
import finalmission.stallstatus.repository.StallStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StallStatusService {

    private final StallStatusRepository stallStatusRepository;
    private final StallRepository stallRepository;
    private final MemberRepository memberRepository;
    private final RandomNickNameClient randomNickNameClient;

    public StallStatusService(StallStatusRepository stallStatusRepository, StallRepository stallRepository, MemberRepository memberRepository, RandomNickNameClient randomNickNameClient) {
        this.stallStatusRepository = stallStatusRepository;
        this.stallRepository = stallRepository;
        this.memberRepository = memberRepository;
        this.randomNickNameClient = randomNickNameClient;
    }

    public StallStatusCreateResponse create(StallStatusCreateRequest request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("해당 유저 정보가 존재하지 않습니다"));
        Stall stall = stallRepository.findById(request.stallId()).orElseThrow(() -> new NotFoundException("해당 사로 정보가 존재하지 않습니다"));
        String randomNickName = randomNickNameClient.getRandomNickname();

        boolean isConflict = stallStatusRepository.existsByMember(member);
        if (isConflict) {
            throw new ConflictException("사용 중인 사로가 존재합니다");
        }

        boolean exists = stallStatusRepository.existsByStall(stall);
        StallStatus stallStatus = new StallStatus(randomNickName, member, stall);
        if (exists) {
            stallStatus.changeStallStatus();
        }

        StallStatus savedStallStatus = stallStatusRepository.save(stallStatus);
        return StallStatusCreateResponse.from(savedStallStatus);
    }

}
