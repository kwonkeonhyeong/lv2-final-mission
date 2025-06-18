package finalmission.stallstatus.controller;

import finalmission.auth.resolver.Authenticated;
import finalmission.auth.resolver.MemberPrincipal;
import finalmission.stallstatus.controller.dto.request.StallStatusCreateRequest;
import finalmission.stallstatus.controller.dto.response.StallStatusCreateResponse;
import finalmission.stallstatus.service.StallStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StallStatusController {

    private final StallStatusService stallStatusService;

    public StallStatusController(StallStatusService stallStatusService) {
        this.stallStatusService = stallStatusService;
    }

    @PostMapping
    public ResponseEntity<StallStatusCreateResponse> postStallStatus(@RequestBody StallStatusCreateRequest request, @Authenticated MemberPrincipal principal) {
        StallStatusCreateResponse response = stallStatusService.create(request, principal.memberId());
        return ResponseEntity.ok(response);
    }
}
