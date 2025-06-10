package finalmission.reservation.repository;

import finalmission.reservation.entity.StallReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StallReservationRepository extends JpaRepository<StallReservation, Long> {
}
