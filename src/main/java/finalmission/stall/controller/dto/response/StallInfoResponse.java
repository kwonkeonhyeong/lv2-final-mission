package finalmission.stall.controller.dto.response;

import finalmission.stall.entity.Stall;

public record StallInfoResponse(
        String name
) {
    public StallInfoResponse(Stall stall) {
        this(stall.getName());
    }
}
