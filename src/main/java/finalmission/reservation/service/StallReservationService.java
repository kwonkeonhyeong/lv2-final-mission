package finalmission.reservation.service;

import finalmission.exception.WailetCustomException;
import finalmission.reservation.dto.request.StallUseRequest;
import finalmission.reservation.entity.StallReservation;
import finalmission.reservation.repository.StallReservationRepository;
import finalmission.toilet.entity.Stall;
import finalmission.toilet.repository.StallRepository;
import finalmission.user.entity.Member;
import finalmission.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class StallReservationService {

    private final StallReservationRepository stallReservationRepository;
    private final StallRepository stallRepository;
    private final MemberRepository memberRepository;
    private final RandomNameClient randomNameClient;

    public StallReservationService(StallReservationRepository stallReservationRepository, StallRepository stallRepository, MemberRepository memberRepository, RandomNameClient randomNameClient) {
        this.stallReservationRepository = stallReservationRepository;
        this.stallRepository = stallRepository;
        this.memberRepository = memberRepository;
        this.randomNameClient = randomNameClient;
    }

    public void enterStall(StallUseRequest request) {
        String email = request.email();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new WailetCustomException("유저를 찾을 수 없습니다."));
        Stall stall = stallRepository.findById(request.stallId()).orElseThrow();

        String reservationStatus = "입장";

        if ("사용중".equals(stall.getStatus())) {
            reservationStatus = "대기";
        }

        String randomName = randomNameClient.getRandomName();
        // todo : request에서 사로ID와 유저 ID를 받는다.
        StallReservation stallReservation = new StallReservation(randomName, reservationStatus, stall, member);
        stallReservationRepository.save(stallReservation);
    }


}
