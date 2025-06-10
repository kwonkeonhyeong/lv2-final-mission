package finalmission.reservation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record StallUseRequest(
        @NotNull
        Long stallId,
        @Email
        String email
) {
}
