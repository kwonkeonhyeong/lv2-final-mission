package finalmission.reservation.controller;

import finalmission.reservation.dto.request.StallUseRequest;
import finalmission.reservation.dto.response.StallUseResponse;
import finalmission.reservation.service.StallReservationService;
import finalmission.user.auth.resolver.AuthorizedMember;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StallReservationController {

    private final StallReservationService stallReservationService;

    public StallReservationController(StallReservationService stallReservationService) {
        this.stallReservationService = stallReservationService;
    }

    /*@PostMapping("/stall/reservation")
    public ResponseEntity<StallUseResponse> postStallUsing(@AuthorizedMember String email, StallUseRequest request) {
        stallReservationService.enterStall(email, request);
        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/stall/reservation")
    public ResponseEntity<StallUseResponse> postStallUsing(@RequestBody @Valid StallUseRequest request) {
        stallReservationService.enterStall(request);
        return ResponseEntity.ok().build();
    }
}
